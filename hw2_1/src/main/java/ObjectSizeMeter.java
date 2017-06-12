import java.util.function.Supplier;

/**
 * Calculate object size in memory
 */

public class ObjectSizeMeter {

    @FunctionalInterface
    public interface ObjectCreator<T>{
        T create();
    }

    public static <T> long getSizeInBytes(Supplier<T> createFunc){
        return getSizeInBytes(createFunc,1_000_000);
    }

    public static <T> long getSizeInBytes(Supplier<T> createFunc, int elementsCount){
        runGC();
        Object[] array = new Object[elementsCount];
        long memBefore = getUsedMemory();
        for (int i=0; i<elementsCount;i++){
            Object object = createFunc.get();
            array[i]=object;
        }
        long memAfter = getUsedMemory();
        return Math.round((float)(memAfter-memBefore)/elementsCount);
    }

    protected static void runGC() {
        try {
            System.gc();
            Thread.sleep(100);
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    protected static long getUsedMemory(){
        Runtime runtime = Runtime.getRuntime ();
        return runtime.totalMemory()-runtime.freeMemory();
    }
}
