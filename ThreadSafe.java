import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafe {
//    private volatile int sheepCount = 0; Not so used! Can show up on exam!
//    private AtomicInteger sheepCount = new AtomicInteger(0);
    private int sheepCount = 0;
    //SheepManager
//    private void incrementAndReport(){
//        synchronized (this){              //SYNCHRONIZED BLOCK
//            System.out.print((++sheepCount) + " ");
//        }
//    } EQUIVALENT WITH:
    private synchronized void incrementAndReport(){     //SYNCHRONIZED METHOD MODIFIER
        System.out.print((++sheepCount) + " ");
    }
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        try {
            var manager = new ThreadSafe();
            for (int i = 0; i < 10; i++)
                service.submit(manager::incrementAndReport);
        }finally{
            service.shutdown();
        }
    }
}
