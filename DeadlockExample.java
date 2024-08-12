import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class DeadlockExample {}

class Food{}
class Water{}
record Fox(String name){
    public void eatAndDrink(Food food, Water water){
        synchronized (food){
            System.out.println(name() + " got food!");
            move();
            synchronized (water){
                System.out.println(name() + " got water!");
            }
        }
    }
    public void drinkAndEat(Food food, Water water){
        synchronized (water){
            System.out.println(name() + " got water!");
            move();
            synchronized (food){
                System.out.println(name() + " got food!");
            }
        }
    }
    private void move() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
    }
    public static void main(String[] args) {
        // Create participants and resources
        Fox foxy = new Fox("Foxy");
        Fox tails = new Fox("Tails");
        Food food = new Food();
        Water water = new Water();
        // Process data
        var service = Executors.newScheduledThreadPool(10);
        try {
            service.submit(()->foxy.eatAndDrink(food,water));
            service.submit(()->tails.drinkAndEat(food,water));
        } finally {
            service.shutdown();
        }
    }
}