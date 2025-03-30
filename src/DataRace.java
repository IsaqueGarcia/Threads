public class DataRace {

    public static void main(String[] args) {

        SharedClass shared = new SharedClass();

        Thread thread1 = new Thread(() -> {
            for(int i = 0; i < Integer.MAX_VALUE; i++){
                shared.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for(int i = 0; i < Integer.MAX_VALUE; i++){
                shared.checkDataRace();
            }
        });

        thread1.start();
        thread2.start();
    }

    public static class SharedClass{

        private volatile int x = 0; //using volatile to prevent data race
        private volatile int y = 0; //using volatile to prevent data race

        public void increment(){
            x++;
            y++;
        }

        public void checkDataRace(){
            if(y > x){
                System.out.println("y > x data race detected y value: " + y + " x value: " + x);
            }
        }

    }
}
