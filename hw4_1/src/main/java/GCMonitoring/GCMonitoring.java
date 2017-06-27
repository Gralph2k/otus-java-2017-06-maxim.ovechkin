package GCMonitoring;
/**
 * Created by maxim.ovechkin on 27.06.2017.
 */
import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.List;

public class GCMonitoring {
    public static void install() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        GCLogAggregator gcLogAggregator = new GCLogAggregator();

        for (GarbageCollectorMXBean gcbean : gcbeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    gcLogAggregator.log(info.getGcName(), info.getGcAction(), info.getGcInfo().getDuration());
                    gcLogAggregator.printAggLog();
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }
}

