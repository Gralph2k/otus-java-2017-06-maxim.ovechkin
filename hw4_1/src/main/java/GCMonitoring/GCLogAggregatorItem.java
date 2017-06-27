package GCMonitoring;

/**
 * Created by maxim.ovechkin on 27.06.2017.
 */
class GCLogAggregatorItem {
    private String name;
    private int runCount = 0;
    private long totalDurationMs = 0;
    private String type;

    public String getType() {
        return type;
    }

    public GCLogAggregatorItem(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getRunCount() {
        return runCount;
    }


    public long getTotalDurationMs() {
        return totalDurationMs;
    }

    public long getAvgDuration() {
        return totalDurationMs / runCount;
    }

    public void aggregateLog(long durationMs) {
        runCount++;
        totalDurationMs += durationMs;
    }

}