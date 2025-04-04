import java.util.*;
import java.util.concurrent.locks.*;

public class ProductReviewsService {

    /**
     * Product Reviews Service
     * After we learned about advanced locks and read-write locks, we told our friend Bob that we now know how to protect shared resources with special locks that keep our application thread-safe and efficient.
     *
     * Bob just completed a mission-critical class for his online store that maintains the store products' reviews, and he asked us for help.
     *
     * He already implemented all the business logic. However, not knowing which locks to use, he left us blank methods getLockForXXXX() where we can provide his business logic methods with the correct locks.
     *
     *
     *
     * Please complete the class in the appropriate sections to help Bob keep his class thread-safe and performant.
     */


    private final HashMap<Integer, List<String>> productIdToReviews;

    // Create your member variables here
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    Lock writeLock = lock.writeLock();
    Lock readLock = lock.readLock();



    /********* DO NOT MODIFY THIS SECTION **************/

    public ProductReviewsService() {
        this.productIdToReviews = new HashMap<>();
    }

    /**
     * Adds a product ID if not present
     */
    public void addProduct(int productId) {
        Lock lock = getLockForAddProduct();

        lock.lock();

        try {
            if (!productIdToReviews.containsKey(productId)) {
                productIdToReviews.put(productId, new ArrayList<>());
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Removes a product by ID if present
     */
    public void removeProduct(int productId) {
        Lock lock = getLockForRemoveProduct();

        lock.lock();

        try {
            if (productIdToReviews.containsKey(productId)) {
                productIdToReviews.remove(productId);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Adds a new review to a product
     * @param productId - existing or new product ID
     * @param review - text containing the product review
     */
    public void addProductReview(int productId, String review) {
        Lock lock = getLockForAddProductReview();

        lock.lock();

        try {
            if (!productIdToReviews.containsKey(productId)) {
                productIdToReviews.put(productId, new ArrayList<>());
            }
            productIdToReviews.get(productId).add(review);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns all the reviews for a given product
     */
    public List<String> getAllProductReviews(int productId) {
        Lock lock = getLockForGetAllProductReviews();

        lock.lock();

        try {
            if (productIdToReviews.containsKey(productId)) {
                return Collections.unmodifiableList(productIdToReviews.get(productId));
            }
        } finally {
            lock.unlock();
        }

        return Collections.emptyList();
    }

    /**
     * Returns the latest review for a product by product ID
     */
    public Optional<String> getLatestReview(int productId) {
        Lock lock = getLockForGetLatestReview();

        lock.lock();

        try {

            if (productIdToReviews.containsKey(productId) && !productIdToReviews.get(productId).isEmpty()) {
                List<String> reviews = productIdToReviews.get(productId);
                return Optional.of(reviews.get(reviews.size() - 1));
            }
        } finally {
            lock.unlock();
        }

        return Optional.empty();
    }

    /**
     * Returns all the product IDs that contain reviews
     */
    public Set<Integer> getAllProductIdsWithReviews() {
        Lock lock = getLockForGetAllProductIdsWithReviews();

        lock.lock();

        try {
            Set<Integer> productsWithReviews = new HashSet<>();
            for (Map.Entry<Integer, List<String>> productEntry : productIdToReviews.entrySet()) {
                if (!productEntry.getValue().isEmpty()) {
                    productsWithReviews.add(productEntry.getKey());
                }
            }
            return productsWithReviews;
        } finally {
            lock.unlock();
        }
    }

    /********* END OF UNMODIFIABLE SECTION **************/


    Lock getLockForAddProduct() {
        // Add code here
        return writeLock;
    }

    Lock getLockForRemoveProduct() {
        // add code here
        return writeLock;
    }

    Lock getLockForAddProductReview() {
        // add code here
        return writeLock;
    }

    Lock getLockForGetAllProductReviews() {
        // add code here
        return readLock;
    }

    Lock getLockForGetLatestReview() {
        // add code here
        return readLock;
    }

    Lock getLockForGetAllProductIdsWithReviews() {
        // add code here
        return readLock;
    }
}