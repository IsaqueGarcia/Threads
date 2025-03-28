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

        private int items = 0;

        public void increment(){
            items++;
        }

        public void decrement(){
            items--;
        }

        public int getItems(){
            return items;
        }

    }

}
