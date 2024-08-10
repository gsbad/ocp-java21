import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafe {
    private int sheepCount = 0;
    //SheepManager
    private void incrementAndReport(){
        System.out.print(++sheepCount + " ");
    }
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        try {
            ThreadSafe manager = new ThreadSafe();
            for (int i = 0; i < 10; i++) service.submit(manager::incrementAndReport);
        }finally{
            service.shutdown();
        }
    }
}
