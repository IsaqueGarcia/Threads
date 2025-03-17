public class InterruptThread {

    public static void main(String[] args) {

        Thread thread = new Thread(new CreatingABadThread());
        Thread thread2 = new Thread(new ThreadTryCatch());

        thread.start();
        thread2.start();

        //thread.setDaemon(true); We can use this, when we are running an lib with no thread catch to interrupt exception

        thread.interrupt(); //If the implementation don't have a way to stop the thread, like a try/cath or a valid interrupt check, this not working...
        thread2.interrupt();

        thread2.interrupt();
    }

    public static class CreatingABadThread implements Runnable{

        @Override
        public void run(){
            for(int i = 0; i <= 1000000000; i++){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Thread stopped 1!");
                    return;
                }
                System.out.println("Counting Numbers");
            }
        }
    }

    public static class ThreadTryCatch implements Runnable{
        @Override
        public void run(){
            try{
                for(int i = 0; i <= 1000000000; i++){
                    Thread.sleep(500000);
                    System.out.println("Counting Numbers");
                }
            }catch (InterruptedException e){
                System.out.println("Thread stopped 2!");
            }
        }
    }

}
