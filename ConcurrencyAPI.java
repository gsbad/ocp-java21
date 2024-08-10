import java.util.concurrent.*;

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
    public static void teste1(String[] args) {
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

    private static int counter = 0;
    public static void teste2(String[] args) throws Exception{

        ExecutorService service = Executors.newSingleThreadExecutor(); //Not AutoCloseable, not work with try-with-resources

        try{
            Future<?> result = service.submit(() -> {
                for (int i = 0; i < 1_000_000; i++) counter++;
            });
            result.get(10, TimeUnit.SECONDS);
            System.out.println("Reached!");
        } catch (TimeoutException e) {
            System.out.println("Not reached at time!");
        } finally {
            service.shutdown();
        }
    }


    public static void teste3(String[] args) throws Exception{

        var service = Executors.newSingleThreadExecutor();

        try{
            Future<Integer> result = service.submit(() -> 38 + 11);
            System.out.println(result.get()); //49
        }finally {
            service.shutdown();
        }
        service.awaitTermination(1, TimeUnit.MINUTES);

        if (service.isTerminated()) System.out.println("Terminated!");
    }

    public static void main(String[] args) throws Exception{

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        Runnable task1 = () -> System.out.println("Tarefa 1");
        Callable<String> task2 = () -> "Tarefa 2";

        ScheduledFuture<?> r1 = service.schedule(task1, 10, TimeUnit.SECONDS);
        ScheduledFuture<?> r2 = service.schedule(task2, 7, TimeUnit.SECONDS);
    }
}