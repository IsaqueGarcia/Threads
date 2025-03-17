import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    public static void main(String[] args) {
        executeAll();
    }

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        for(Runnable task : tasks){
            Thread thread = new Thread(task);
            System.out.println("Executing a new thread with a new task");
            thread.start();
        }
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public static void executeAll() {
        List<Runnable> runnableTasks = new ArrayList<>();

        Task task1 = new Task("First task");
        Task task2 = new Task("Second task");

        runnableTasks.add(task1);
        runnableTasks.add(task2);

        new MultiExecutor(runnableTasks);
    }

    private static class Task implements Runnable {

        private String name;

        public Task(String name){
            this.name = name;
        }

        @Override
        public void run(){
            System.out.println("This is a new task called " + name);
        }
    }

}
