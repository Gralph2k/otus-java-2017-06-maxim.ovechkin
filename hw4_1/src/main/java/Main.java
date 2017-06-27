import GCMonitoring.GCMonitoring;

/**
 * Created by maxim.ovechkin on 27.06.2017.
 */
public class Main {

    public static void main(String[] args)  {
        GCMonitoring.installGCMonitoring();
        runStand();
    }

    private static void runStand() {
        MemoryLeakClass memoryLeakClass = new MemoryLeakClass(10_000_000,500_000);
        try {
            while (true) {
                memoryLeakClass.run();
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


}
