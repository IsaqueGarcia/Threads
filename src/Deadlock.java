import java.util.Random;

public class Deadlock {

    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        Thread routeA = new Thread(new RouteA(intersection));
        Thread routeB = new Thread(new RouteB(intersection));

        routeA.start();
        routeB.start();
    }

    public static class RouteA extends Thread {
        private Intersection intersection;
        Random random = new Random();

        public RouteA(Intersection intersection){
            this.intersection = intersection;
        }

        @Override
        public void run(){
            while(true){
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                intersection.takeRouteA();
            }
        }

    }

    public static class RouteB extends Thread {
        private Intersection intersection;
        Random random = new Random();

        public RouteB(Intersection intersection){
            this.intersection = intersection;
        }

        @Override
        public void run(){
            while(true){
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                intersection.takeRouteB();
            }
        }

    }

    public static class Intersection{
        Object routeA = new Object();
        Object routeB = new Object();


        public void takeRouteA() {
            synchronized (routeA){
                System.out.println("Route A is locked by Thread " + Thread.currentThread().getName());

            synchronized (routeB){
                System.out.println("The route choice is A");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            }
        }

        public void takeRouteB() {
            synchronized (routeA){
                System.out.println("Route B is locked by Thread " + Thread.currentThread().getName());

                synchronized (routeB){ //If i change the order of lock, this will generate a deadlock condition
                    System.out.println("The route choice is B");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }

}
