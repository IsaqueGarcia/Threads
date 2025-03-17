import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HackersThreads {

    public static int MAX_PASSWORD = 9999;

    public static void main(String[] args) {

        Random random = new Random();
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        List<Thread> threads = new ArrayList<>();

        threads.add(new AscHackerThread(vault));
        threads.add(new DescHackerThread(vault));
        threads.add(new PoliceThread());

        threads.forEach(Thread::start);
    }

    private static class Vault {

        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int password) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
            return this.password == password;
        }

    }

    private static class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getName());
            this.setPriority(MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    private static class AscHackerThread extends HackerThread {

        public AscHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASSWORD; guess++) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println("The hacker " + this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescHackerThread extends HackerThread {

        public DescHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD; guess >= 1; guess--) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println("The hacker " + this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }

    }

    private static class PoliceThread extends Thread {

        @Override
        public void run() {
            for (int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(i);
            }
            System.out.println("Game over for you!!");
            System.exit(0);
        }

    }

}
