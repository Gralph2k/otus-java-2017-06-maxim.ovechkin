import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.*;

/**
 * Created by maxim.ovechkin on 20.06.2017.
 */
public class MyArrayListTest {


    @Test
    public void addAll(){
        Integer[] values = {5,1,2,3,6};
        MyArrayList<Integer> myArrayList = new MyArrayList<Integer>();
        Collections.addAll(myArrayList,values);
        assertEquals(myArrayList.size(),5);
    }

    @Test
    public void copy(){
        ArrayList<Integer> source = new ArrayList<Integer>();
        ArrayList<Integer> destination = new ArrayList<Integer>();
        Collections.addAll(destination, 0, 0, 0, 0, 0);
        Collections.addAll(source, 1, 2, 3, 4, 5);
        Collections.copy(destination,source);
        for (int i=0;i<source.size();i++) {
            assertEquals(destination.get(i),source.get(i));
        }
    }

    @Test
    public void sort(){
        Integer[] values = {5,1,2,3,6};
        MyArrayList<Integer> myArrayList = new MyArrayList<Integer>();
        Collections.addAll(myArrayList,values);
        Collections.sort(myArrayList);
        assertEquals(myArrayList.get(0).intValue(),1);
        assertEquals(myArrayList.get(1).intValue(),2);
        assertEquals(myArrayList.get(2).intValue(),3);
        assertEquals(myArrayList.get(3).intValue(),5);
        assertEquals(myArrayList.get(4).intValue(),6);
    }

}