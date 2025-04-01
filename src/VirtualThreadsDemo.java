import java.util.ArrayList;
import java.util.List;

public class VirtualThreadsDemo {

    private static final int NUMBERS_OF_THREADS = 100;

    public static void main(String[] args) throws InterruptedException {

        List<Thread> virtualThreads = new ArrayList<>();

        //Thread platformThread = Thread.ofPlatform().unstarted(runnable);
        //platformThread.start();
        //platformThread.join();

        for(int i = 0; i < NUMBERS_OF_THREADS; i++){
            Thread virtualThread = Thread.ofVirtual().unstarted(new BlockingThread());
            virtualThreads.add(virtualThread);
        }

        virtualThreads.forEach(Thread::start);

        for(Thread thread : virtualThreads){
            thread.join();
        }

    }

    public static class BlockingThread implements Runnable{
        @Override
        public void run() {
            System.out.println("Inside Thread: " + Thread.currentThread() + " before blocking call");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Inside Thread: " + Thread.currentThread() + " after blocking call");
        }
    }

}
