package category.greedy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskScheduler {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public int getMinTimeCost(List<String> tasks, int minDistance) {
        if (tasks == null || tasks.size() == 0 || minDistance < 0) {
            return 0;
        }

        Map<String, Integer> taskStastics = getTaskStastics(tasks);

        return 0;
    }

    private Map<String, Integer> getTaskStastics(List<String> tasks) {
        Map<String, Integer> stastics = new HashMap<String, Integer>();

        for (String task : tasks) {
            if (stastics.containsKey(task)) {
                stastics.put(task, stastics.get(task) + 1);
            } else {
                stastics.put(task, 1);
            }
        }

        return stastics;
    }
}
