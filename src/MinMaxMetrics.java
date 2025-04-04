public class MinMaxMetrics {

    /***
     * In this exercise, we are going to implement a class called MinMaxMetrics .
     *
     * A single instance of this class will be passed to multiple threads in our application.
     *
     * MinMaxMetrics is an analytics class used to keep track of the minimum and the maximum of a particular business or performance metric in our application.
     *
     * Example:
     *
     * A stock trading application that keeps track of the minimum and maximum price of the stock daily.
     *
     *
     *
     * The class will have 3 methods:
     *
     * addSample(..) - Takes a new sample.
     *
     * getMin() - Returns the sample with the minimum value we have seen so far.
     *
     * getMax() - Returns the sample with the maximum value we have seen so far.
     *
     *
     *
     * Notes:
     *
     * - Each method can be called concurrently by any given number of threads, so the class needs to be thread-safe.
     *
     * - In addition, this class is used for analytics, so it needs to be as performant as possible, as we don't want it to slow down our business logic threads too much.
     *
     * - Threads that call getMin() or getMax() are interested in only one of the values and are never interested in both the min and the max at the same time
     *
     *
     * Please implement MinMaxMetrics below:
     *
     *
     *
     * Important Note:
     *
     * Only the logic of the class is unit tested, and it is impossible to test a class's thread safety.
     *
     * The solution to the exercise is provided in the next lecture.
     *
     */



    // Add all necessary member variables
    private volatile long min;
    private volatile long max;

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {
        // Add code here
        this.min = Long.MIN_VALUE;
        this.max = Long.MAX_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public synchronized void addSample(long newSample) {
        // Add code here
            this.max = Math.max(newSample, this.max);
            this.min = Math.min(newSample, this.min);
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        // Add code here
        return this.min;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        // Add code here
        return this.max;
    }


}
