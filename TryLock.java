import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock {
    public static void printHello(Lock lock){
        try {
            lock.lock();
            System.out.println("\n\nHello!");
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException{
        ReentrantLock lock = new ReentrantLock();
        //While not on the exam 'ReentrantReadWriteLock' is a really useful class!
        new Thread(()->printHello(lock)).start();

        if (lock.tryLock(10, TimeUnit.SECONDS)){
            try {
                System.out.println("Lock obtained! Entering protected code!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }else{
            System.out.println("Unable to acquire lock! Doing something else!");
        }
    }
}
