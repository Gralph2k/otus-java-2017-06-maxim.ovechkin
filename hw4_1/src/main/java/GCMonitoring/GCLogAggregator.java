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
        logger.info("name:{}\ttype:{}\tduration:{} ms   \t total run count:{}\t Avg duration:{}ms ", item.getName(), item.getType(), durationMs,item.getRunCount(),item.getAvgDuration());
        return item;
    }
}