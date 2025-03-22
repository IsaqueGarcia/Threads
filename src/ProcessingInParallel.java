import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessingInParallel {

    static List<String> numbers = new ArrayList<>(Arrays.asList(
            "1", "5", "3", "7", "9", "2", "8", "0", "4", "6",
            "10", "20", "30", "40", "50", "60", "70", "80", "90", "100",
            "110", "120", "130", "140", "150", "160", "170", "180", "190", "200",
            "210", "220", "230", "240", "250", "260", "270", "280", "290", "300",
            "310", "320", "330", "340", "350", "360", "370", "380", "390", "400",
            "410", "420", "430", "440", "450", "10", "470", "480", "490", "500",
            "510", "520", "530", "540", "550", "560", "570", "580", "590", "600",
            "610", "620", "630", "640", "650", "660", "670", "680", "690", "700",
            "710", "720", "730", "740", "750", "760", "770", "780", "790", "800",
            "810", "820", "830", "840", "10", "860", "870", "880", "890", "900",
            "910", "920", "930", "940", "950", "960", "970", "980", "990", "1000"
    ));

    static int count = 0;

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        //singleThread();
        multipleThread();
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        System.out.println("Duration: ".concat(String.valueOf(duration)));

    }

    public static void singleThread(){
        countNumbers(10, 0, numbers.size());
        System.out.println(count);
    }

    public static void multipleThread() {
        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < 2 ; i++){
            if(i == 0){
                Thread thread = new Thread( () -> {
                    countNumbers(10, 0, numbers.size() / 2);
                });
                threads.add(thread);
            }

            if(i == 1){
                Thread thread = new Thread( () -> {
                    countNumbers(10,(numbers.size() / 2) , numbers.size());
                });
                threads.add(thread);
            }
        }

        try{
            threads.forEach(Thread::start);

            for (Thread thread : threads){
                thread.join();
            }
        }catch (InterruptedException e){
        }

        System.out.println(count);

    }

    public static void countNumbers(int number, int start, int size){
        for(int i = start; i < size; i++){
            count = Integer.parseInt(numbers.get(i)) == number ? count + 1 : count;
        }
    }



}
