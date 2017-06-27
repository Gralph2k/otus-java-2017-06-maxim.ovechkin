import GCMonitoring.GCMonitoring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by maxim.ovechkin on 27.06.2017.
 */
public class MemoryLeakClass {
    static Logger logger = LoggerFactory.getLogger(GCMonitoring.class);
    private int addElementsCount = 1_000_000;
    private int removeElementsCount = addElementsCount /2;
    ArrayList<Integer> array;

    public MemoryLeakClass(int addElementsCount, int removeElementsCount ){
        this.addElementsCount = addElementsCount;
        this.removeElementsCount = removeElementsCount;
        array = new ArrayList<>();
    }

    void run(){
        addElements(addElementsCount);
        removeElements(removeElementsCount);
        logger.debug("Array size={} MaxElement={}",array.size(), array.get(array.size()-1));
    }

    private void addElements(int cnt){
        int arraySize = array.size();
        for (int i = 0; i< cnt; i++) {
            array.add(arraySize+i+1);
        }
    }

    private void removeElements(int cnt){
        for (int i=0; i<cnt; i++) {
            if (array.size()==0) {
                break;
            }
            array.remove(array.size()-1);
        }
    }

}
