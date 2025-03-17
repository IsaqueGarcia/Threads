//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        /*
         * Basic concepts to understand how to use threads...
         */

        Thread thread = new NewThread();
        thread.setName("New Worker");
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("Thread running before new Thread " + Thread.currentThread().getName());
        thread.start();
        System.out.println("Thread running after new Thread " + Thread.currentThread().getName());

    }

    public static class NewThread extends Thread {
        @Override
        public void run(){
            System.out.println("This is a new thread running " + Thread.currentThread().getName()
                    + " with priority " +  Thread.currentThread().getPriority());
            //throw new RuntimeException("Simulating an Exception to get caught!");

            this.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println("An error was ocurred " + e.getMessage());
                }
            });
        }
    }
}