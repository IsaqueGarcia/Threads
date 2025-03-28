public class Synchronized {

    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementThread incrementThread = new IncrementThread(inventoryCounter);
        DecrementThread decrementThread = new DecrementThread(inventoryCounter);

        incrementThread.start();
        decrementThread.start();

        incrementThread.join();
        decrementThread.join();

        System.out.println("Remaning items: " + inventoryCounter.getItems());

    }

    public static class IncrementThread extends Thread{

        InventoryCounter inventoryCounter;

        public IncrementThread(InventoryCounter inventoryCounter){
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for(int i = 0; i < 10000; i++){
                inventoryCounter.increment();
            }
        }
    }

    public static class DecrementThread extends Thread{
        InventoryCounter inventoryCounter;

        public DecrementThread(InventoryCounter inventoryCounter){
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run(){
            for(int i = 0; i < 10000; i++){
                inventoryCounter.decrement();
            }
        }
    }

    public static class InventoryCounter{

        final Object lock = new Object();

        private int items = 0;

        public void increment(){
            synchronized(this.lock){
                items++;
            }
        }

        public synchronized void decrement(){
            synchronized (this.lock){
                items--;
            }
        }

        public int getItems(){
            synchronized (this.lock){
                return items;
            }
        }

    }

}
