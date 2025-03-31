import java.util.concurrent.atomic.AtomicReference;

public class AtomicExamples {

    public static void main(String[] args) {

        String oldName = "Isaque";
        String newName = "Helen";

        AtomicReference<String> atomicReference = new AtomicReference<>(oldName);

        if(atomicReference.compareAndSet(oldName, newName)){
            System.out.println("New value is " + atomicReference.get());
        }else{
            System.out.println("Condition is not true");
        }

    }

}
