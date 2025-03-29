import java.util.Random;

public class AtomicOperation {

    public static void main(String[] args) {
        Metrics metrics = new Metrics();

        BussinesLogic bussinesLogic1 = new BussinesLogic(metrics);
        BussinesLogic bussinesLogic2 = new BussinesLogic(metrics);
        MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);

        bussinesLogic1.start();
        bussinesLogic2.start();
        metricsPrinter.start();
    }

    public static class MetricsPrinter extends Thread {
        Metrics metrics;

        public MetricsPrinter(Metrics metrics){
            this.metrics = metrics;
        }

        @Override
        public void run(){
            while(true){

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                double currentAverage = metrics.getAverage();

                System.out.println("Current average is " + currentAverage);

            }
        }


    }

    public static class BussinesLogic extends Thread{
        Metrics metrics;
        Random random = new Random();

        public BussinesLogic(Metrics metrics){
            this.metrics = metrics;
        }

        @Override
        public void run(){

            while (true){
                long start = System.currentTimeMillis();

                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                long end = System.currentTimeMillis();

                metrics.addSample(end - start);
            }
        }


    }

    public static class Metrics{

        private long count = 0;
        private volatile double average = 0.0;

        public synchronized void addSample(long sample) {
            double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count;
        }

        public double getAverage(){
            return  average;
        }

    }

}
