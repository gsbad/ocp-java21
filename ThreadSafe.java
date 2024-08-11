import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafe {
//    private volatile int sheepCount = 0; Not so used! Can show up on exam!
//    private AtomicInteger sheepCount = new AtomicInteger(0);
    private int sheepCount = 0;
    //SheepManager
    private void incrementAndReportBlockSync(){
        synchronized (this){              //SYNCHRONIZED BLOCK
            System.out.print((++sheepCount) + " ");
        }
    } //EQUIVALENT WITH:
    private synchronized void incrementAndReportMethodModifier(){     //SYNCHRONIZED METHOD MODIFIER
        System.out.print((++sheepCount) + " ");
    }
    private synchronized void incrementAndReportWithLock(){
        var lock = new ReentrantLock(true);
        //LOCK: ALSO EQUIVALENT (fair method parameter on 'true' for when ordering is required
        try {
            lock.lock();
            System.out.print((++sheepCount) + " ");
        } finally {
            //'IllegalMonitorStateException' if the lock does not exist
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        try {
            var manager = new ThreadSafe();
            for (int i = 0; i < 10; i++)
                service.submit(manager::incrementAndReportWithLock);
        }finally{
            service.shutdown();
        }
    }
}
