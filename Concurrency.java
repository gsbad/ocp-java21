public class Concurrency {
    /*
    THREAD'S STATES
    1 - NEW
    2 - RUNNABLE
        2.1 - BLOCKED
        2.2 - WAITING
        2.3 - TIMED_WAITING
     3 - TERMINATED
     */
    public static void teste(String[] args) {
        Runnable printInventory = () -> System.out.println("Printing zoo inventory!");
        Runnable printRecords = () -> {
            for (int i = 0; i < 3; i++){
                System.out.println("Printing record: " + i);
            }
        };

        System.out.println("begin");
        new Thread(printInventory).start();
        new Thread(printRecords).start();
        new Thread(printInventory).start();
        System.out.println("end");
    }
}

class Zoo{
    public static void pause(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){}
        System.out.println("Thread finished!");
    }
    public static void teste2(String[] args) {

        var job = new Thread(Zoo::pause);
        System.out.println(job.getState()); //NEW
        job.start();
        System.out.println(job.getState()); //RUNNABLE
        System.out.println("Main method finished!");
    }
}

class CheckResults{
    private static int counter = 0;

    public static void teste3(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 1_000_000; i++) counter++;
        }).start();

        while (counter < 1_000_000){
            System.out.println("Not reached yet!");
        }
        System.out.println("Reached " + counter + "!");
    }
}

class CheckResultsWithSleep{
    private static int counter = 0;

    public static void teste4(String[] args) {
        var job = new Thread(()->{
            for (int i = 0; i < 1_000_000; i++) counter++;
        });
        System.out.println(job.getState()); //NEW
        job.start();
        System.out.println(job.getState());
        while (counter < 1_000_000){
            System.out.println("Not reached yet!"); //RUNNABLE
            try {
                Thread.sleep(1_000); //1 SECOND
                System.out.println(job.getState()); //TERMINATED
            }catch (InterruptedException e){
                System.out.println("Interrupted!");
            }
        }
        System.out.println("Reached " + counter + "!");
    }
}

class CheckResultsWithSleepAndInterrupt{
    private static int counter = 0;

    public static void main(String[] args) {
        final var mainThread = Thread.currentThread();
        var job = new Thread(()->{
            for (int i = 0; i < 1_000_000; i++) counter++;
            mainThread.interrupt(); //INTERRUPT THE MAIN THREAD IF TIMED_WAITING!
        });
        System.out.println(job.getState()); //NEW
        job.start();
        System.out.println(job.getState());
        while (counter < 1_000_000){
            System.out.println("Not reached yet!"); //RUNNABLE
            try {
                Thread.sleep(1_000); //1 SECOND
                System.out.println(job.getState()); //(UNREACHABLE) TERMINATED
            }catch (InterruptedException e){
                System.out.println("Interrupted!"); //CATCHED BECAUSE WAS INTERRUPTED!
            }
        }
        System.out.println("Reached " + counter + "!");
    }
}