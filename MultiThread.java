public class MultiThread {
    public static void teste1(String[] args) {
        Thread task1 = new Thread(new PrintHelloTask("Task 1"));
        Thread task2 = new Thread(new PrintHelloTask("Task 2"));
        Thread task3 = new Thread(new PrintHelloTask("Task 3"));
        Thread task4 = new Thread(new PrintHelloTask("Task 4"));

        task1.start();
        task2.start();
        task3.start();
        task4.start();
    }
}
class Counter{
    public static void teste2(String[] args) {
        CounterTask counterTask = new CounterTask();
        Thread counter1 = new Thread(counterTask);
        Thread counter2 = new Thread(counterTask);
        Thread counter3 = new Thread(counterTask);
        Thread counter4 = new Thread(counterTask);

        counter1.start();
        counter2.start();
        counter3.start();
        counter4.start();
    }
}

class Data{
    private boolean outOfSync;
    private int value;

    public boolean isOutOfSync(){
        return outOfSync;
    }
    public void setOutOfSync(boolean outOfSync){
        this.outOfSync = outOfSync;
    }
    public int getValue(){
        return value;
    }
    public void setValue(int value){
        this.value = value;
    }
    public synchronized void sync(){
        System.out.println("Synchronizing data...");

        try {
            if (!outOfSync)
                this.wait();
            outOfSync = false;
            Thread.sleep(5000);
            System.out.println("Synchronized!");
            this.notifyAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public synchronized void read(){
        System.out.println("Reading data...");

        try {
            if (outOfSync)
                this.wait();

            Thread.sleep(2000);
            System.out.println("Data: "+value);
            this.notifyAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class ReadDataThread implements Runnable {
    private Data data;

    public ReadDataThread(Data data){
        super();
        this.data = data;
    }

    @Override
    public void run(){
        data.read();
    }
}
class SyncDataThread implements Runnable {
    private Data data;

    public SyncDataThread(Data data){
        super();
        this.data = data;
    }

    @Override
    public void run(){
        data.sync();
    }
}
class OnlineOfflineSystem{
    public static void teste3(String[] args) {
        Data data = new Data();
        //Depois comentar para simular loop eterno no Sync
        data.setOutOfSync(true);
        Thread readData = new Thread(new ReadDataThread(data));
        Thread syncData = new Thread(new SyncDataThread(data));

        readData.start();
        //syncData.setDaemon(true);
        syncData.start();
    }
}
class Race{
    private volatile boolean finished;
    //private boolean finished;

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
class Runner implements Runnable{
    public Runner(Race race, String runner){
        super();
        this.race = race;
        this.runner = runner;
    }
    public Race race;
    public String runner;

    @Override
    public void run(){
        System.out.println(runner+" running...");
//        Checar se algum corredor encerrou
        while (!race.isFinished()){
            race.setFinished(true);
            System.out.println("Winner: "+runner);
            return;
        }
        System.out.println(runner+" lost :(");
    }
}
class RaceMain{
    public static void teste4(String[] args) {
        Race race = new Race();
        Thread runner1 = new Thread(new Runner(race, "Runner 1"));
        Thread runner2 = new Thread(new Runner(race, "Runner 2"));
        System.out.println("Starting...");
        runner1.start();
        runner2.start();
    }
}
class PrintHelloTask implements Runnable{
    private String name;

    public PrintHelloTask(String name){
        this.name = name;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(60000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Hello, "+name+"!");
    }
}

class CounterTask implements Runnable{
    private int counter;

    @Override
    public void run(){
        synchronized (this){
            counter++;
            System.out.println(Thread.currentThread().getName() + ": "+counter);
        }
    }
}
