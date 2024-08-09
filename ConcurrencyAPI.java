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
        try(ExecutorService service = Executors.newSingleThreadExecutor()) {
            System.out.println("begin");
            service.execute(printInventory);
            service.execute(printRecords);
            service.execute(printInventory);
            System.out.println("end");
        } catch (RejectedExecutionException e) {
            System.out.println("Exception catched!");
        }

    }
}