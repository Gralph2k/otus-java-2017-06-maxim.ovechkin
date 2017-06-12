import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Created by Maxim.Ovechkin on 10.06.2017.
 */
public class Main {

    public static void main(String[] args) {
        int elementsCount = 1_000_000;
        printObjectSize(()->new Integer(1), elementsCount, "Object");
        printObjectSize(()->(Integer) 1, elementsCount, "Primitive type");
        printObjectSize(()->new String(""), elementsCount,  "Empty string");
        printObjectSize(()->new String("Test"), elementsCount,  "String with string pool");
        printObjectSize(()->new String(new char[0] ), elementsCount,  "String without string pool");
        printObjectSize(()->new ObjectSizeMeter(), elementsCount, "Class object");
        printObjectSize(()->new Integer[0], elementsCount, "Integer[0]");
        printObjectSize(()->new Integer[1], elementsCount, "Integer[1]");
        printObjectSize(()->new Integer[2], elementsCount, "Integer[2]");
        printObjectSize(()->new Integer[100], elementsCount, "Integer[100]");
        printObjectSize(()->new ArrayList(0), elementsCount, "ArrayList(0)");
        printObjectSize(()->new ArrayList(100), elementsCount, "ArrayList(100)");
        System.out.println();
        elementsCount = 10_000;
        printObjectSize(()->new Integer(1), elementsCount, "Object");
        printObjectSize(()->(Integer) 1, elementsCount, "Primitive type");
        printObjectSize(()->new String(""), elementsCount,  "Empty string");
        printObjectSize(()->new String("Test"), elementsCount,  "String with string pool");
        printObjectSize(()->new String(new char[0] ), elementsCount,  "String without string pool");
        printObjectSize(()->new ObjectSizeMeter(), elementsCount, "\tClass object");
        printObjectSize(()->new Integer[0], elementsCount, "Integer[0]");
        printObjectSize(()->new Integer[1], elementsCount, "Integer[1]");
        printObjectSize(()->new Integer[2], elementsCount, "Integer[2]");
        printObjectSize(()->new Integer[100], elementsCount, "Integer[100]");
        printObjectSize(()->new ArrayList(0), elementsCount, "ArrayList(0)");
        printObjectSize(()->new ArrayList(100), elementsCount, "ArrayList(100)");

    }


    private static <T> void printObjectSize(Supplier<T> createFunc, int elementsCount,  String comment){
        String result = String.format("Size %d bytes.\t %d elements of {%s}.\t %s", ObjectSizeMeter.getSizeInBytes(createFunc),  elementsCount, createFunc.get().getClass(),  comment);
        System.out.println(result);
    }

}
