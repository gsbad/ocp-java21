import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class ConcurrencyAPI {
    /*
    THREAD'S STATES
    1 - ACTIVE  -> Accepting new tasks / Executing tasks
    (isShutdown() (False) , isTerminated() (False))
    2 - SHUTTING DOWN   -> Rejecting new tasks / Executing tasks
    (isShutdown() (True) , isTerminated() (False))
    3 - TERMINATED   -> Rejecting new tasks / No tasks running
    (isShutdown() (True) , isTerminated() (True))
     */
    public static void main(String[] args) {
        Runnable printInventory = () -> System.out.println("Printing zoo inventory!");
        Runnable printRecords = () -> {
            for (int i = 0; i < 3; i++){
                System.out.println("Printing record: " + i);
            }
        };
        ExecutorService service = Executors.newSingleThreadExecutor(); //Not AutoCloseable, not work with try-with-resources
        try{
            System.out.println("begin\n");

            service.execute(printInventory);
            service.execute(printRecords);
            service.execute(printInventory);

            System.out.println("\nTry (isShutdown): " + service.isShutdown()); //false
            System.out.println("Try: (isTerminated)" + service.isTerminated()); //false

            System.out.println("\nend\n");
        } catch (RejectedExecutionException e) {
            System.out.println("Exception catched!");
        } finally {
            service.shutdown();
            System.out.println("\nFinally (isShutdown): " + service.isShutdown()); //true
            System.out.println("Finally: (isTerminated)" + service.isTerminated()); //false
        }
        System.out.println("\nMainBlock (isShutdown): " + service.isShutdown()); //true
        System.out.println("MainBlock: (isTerminated)" + service.isTerminated()); //false - Only terminated when main thread end
    }
}