package category.design.datastructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class MetricPoint {

    long timestamp;

    double value;

    MetricPoint(double value, long timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }
}

/**
 * [Snap*chat] metrics reporter that can query min max, avg from the last X time period.
 */
public class MetricsReporter {

    private Map<String, MetricType> name2Type;

    private long validTimeInMilliSeconds;

    public MetricsReporter(long validTimeInMilliSeconds) {
        this.validTimeInMilliSeconds = validTimeInMilliSeconds;
        name2Type = new HashMap<String, MetricType>();
    }

    public double getAvg(String metricName) {
        MetricType currentType = name2Type.get(metricName);
        if (currentType == null) {
            throw new RuntimeException();
        }

        return currentType.getAvg();
    }

    public void record(String name, double value, long timestamp) {
        if (!name2Type.containsKey(name)) {
            name2Type.put(name, new MetricType(name, validTimeInMilliSeconds));
        }
        MetricType currentType = name2Type.get(name);
        currentType.record(value, timestamp);
    }
}

class MetricType {

    List<MetricPoint> mins;

    String name;

    double sum;

    long validTimeInMilliSeconds;

    List<MetricPoint> values;

    MetricType(String name, long validTimeInMilliSeconds) {
        this.validTimeInMilliSeconds = validTimeInMilliSeconds;
        this.name = name;
        this.values = new LinkedList<>();
    }

    public double getAvg() {
        removeExpired();
        int size = values.size();

        if (size == 0) {
            return 0;
        } else {
            return sum / size;
        }
    }

    private boolean isExpired(long timestamp) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - validTimeInMilliSeconds > timestamp) {
            return true;
        } else {
            return false;
        }
    }

    public void record(double value, long timestamp) {
        removeExpired();
        values.add(new MetricPoint(value, timestamp));
        sum += value;
    }

    private void removeExpired() {
        while (!values.isEmpty() && isExpired(values.get(0).timestamp)) {
            sum -= values.get(0).value;
            values.remove(0);
        }
    }

}
