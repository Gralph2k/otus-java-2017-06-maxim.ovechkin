package GCMonitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

/**
 * Created by maxim.ovechkin on 27.06.2017.
 */
class GCLogAggregator {
    private static Logger logger = LoggerFactory.getLogger(GCMonitoring.class);
    private HashSet<GCLogAggregatorItem> gcLogAggregatorItems= new HashSet<>();

    GCLogAggregatorItem findByName(String name){
        for (GCLogAggregatorItem row: gcLogAggregatorItems) {
            if (row.getName().equals(name)) {
                return row;
            }
        }
        return null;
    }

    GCLogAggregatorItem log(String name, String type, long durationMs) {
        GCLogAggregatorItem item=findByName(name);
        if (item==null) {
           item = new GCLogAggregatorItem(name, type);
           gcLogAggregatorItems.add(item);
        }
        item.aggregateLog(durationMs);
        return item;
    }

    void printAggLog(){
        String result="";
        for ( GCLogAggregatorItem item:gcLogAggregatorItems) {
            result=result+(String.format("\n\tname:%s\t|type:%s\t|total run count:%d\t|avg duration:%d ms\t|last duration:%s ms ", item.getName(), item.getType(), item.getRunCount(),item.getAvgDuration(), item.getLastDuratuionMs()));
        }
        logger.info(result);

    }
}