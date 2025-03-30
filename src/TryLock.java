import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock {

    public static void main(String[] args) {
        int itens = 100;
        Stock stockCount = new Stock(itens);

        Thread customer1 = new Thread(stockCount);
        Thread customer2 = new Thread(stockCount);

        customer1.start();
        customer2.start();
    }

    private static class Stock extends Thread{

        Lock lock = new ReentrantLock();
        private int stock = 100;
        private int count;


        public Stock(int count){
            this.count = count;
        }

        @Override
        public void run(){
            for(int i = 0; i < count; i++){
                if(lock.tryLock()){
                    try{
                        if(stock > 0){
                            stock--;
                            System.out.println("Actual stock quantity: " + stock + " purchased by " + Thread.currentThread().getName());
                        }
                    }finally {
                        lock.unlock();
                    }
                }else {
                    System.out.println("An customer already purchase this item");
                }
            }
        }
    }
}
