package category.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

public class JobSchedule {

    public static void main(String[] args) {
        // System.out.println(timeCost("aaaaabbbbcccdde", 4));
        System.out.println(scheduleJobs("aaaabbbbccdde", 3));
    }

    /**
     * A follow up question: If the given string can be re-ordered, how will you schedule the jobs so that you can
     * minimize the number of time unit needed to finish all jobs.
     */
    public static int scheduleJobs(String jobs, int n) {
        if (jobs.length() <= 1 || n == 0) {
            return jobs.length();
        }

        Map<Character, Integer> jobStastics = new HashMap<Character, Integer>();
        for (char job : jobs.toCharArray()) {
            if (jobStastics.containsKey(job)) {
                jobStastics.put(job, jobStastics.get(job) + 1);
            } else {
                jobStastics.put(job, 1);
            }
        }

        Map<Integer, List<Character>> countToTasks = new HashMap<Integer, List<Character>>();
        for (Map.Entry<Character, Integer> entry : jobStastics.entrySet()) {
            if (countToTasks.containsKey(entry.getValue())) {
                countToTasks.get(entry.getValue()).add(entry.getKey());
            } else {
                List<Character> tasks = new ArrayList<Character>();
                tasks.add(entry.getKey());
                countToTasks.put(entry.getValue(), tasks);
            }
        }

        UpdateableTreeSet taskPool = new UpdateableTreeSet((job1, job2) -> (Integer.compare(job2.count, job1.count)));
        for (Map.Entry<Integer, List<Character>> entry : countToTasks.entrySet()) {
            taskPool.add(new JobsWithCount(entry.getKey(), entry.getValue()));
        }

        Set<Character> dedupedSet = new HashSet<Character>();
        StringBuilder scheduledTasks = new StringBuilder();

        while (!taskPool.isEmpty()) {
            Iterator<JobsWithCount> iterator = taskPool.iterator();
            while (iterator.hasNext()) {
                JobsWithCount current = iterator.next();

                if (updateTaskPool(taskPool, dedupedSet, scheduledTasks, current)) {
                    break;
                }

                if (!iterator.hasNext()) {
                    scheduledTasks.append('_');
                }
            }

            if (scheduledTasks.length() > n) {
                dedupedSet.remove(scheduledTasks.charAt(scheduledTasks.length() - n - 1));
            }
        }

        return scheduledTasks.length();
    }

    /*
     * Job scheduling. The same type of job can not be done within the given n unit of time. Given a string like
     * "abcacb", each character represents a type of job. And give n = 4, then the job schedule should be like:
     * abc__a_cb This is because after 'a', another 'a' job can not be scheduled again within 4 time unit. So in total,
     * such a job sequence needs 9 time units to finish. Given such a string and such a n, return how many time unit it
     * needs to finish all the jobs.
     */
    public static int timeCost(String jobs, int n) {
        if (n == 0)
            return jobs.length();

        int count = 0;
        Map<Character, Integer> last = new HashMap<>();
        for (int i = 0; i < jobs.length();) {
            if (!last.containsKey(jobs.charAt(i)) || last.get(jobs.charAt(i)) < count - n) {
                last.put(jobs.charAt(i), count);
                i++;
            }
            count++;
        }

        return count;
    }

    private static boolean updateTaskPool(UpdateableTreeSet taskPool, Set<Character> dedupedSet,
            StringBuilder scheduledTasks, JobsWithCount current) {
        for (Character job : current.jobs) {
            if (dedupedSet.add(job)) {
                scheduledTasks.append(job);
                taskPool.updateByMinusCount(current, job);
                return true;
            }
        }

        return false;
    }

    @Test
    public void testWithDifferentCases() {
        Assert.assertEquals(1, scheduleJobs("a", 1));
        Assert.assertEquals(1, scheduleJobs("a", 10));
        Assert.assertEquals(5, scheduleJobs("aa", 3));
        Assert.assertEquals(6, scheduleJobs("aabb", 3));
        Assert.assertEquals("one example is: abcdabcdabe_ab", 14, scheduleJobs("aaaabbbbccdde", 3));
    }

}

class JobsWithCount {

    int count;

    List<Character> jobs;

    public JobsWithCount(int count, List<Character> jobs) {
        this.jobs = jobs;
        this.count = count;
    }

    @Override
    public String toString() {
        return jobs + ":" + count;
    }

    public JobsWithCount withJob(char c) {
        this.jobs.add(c);
        return this;
    }
}

class UpdateableTreeSet extends TreeSet<JobsWithCount> {

    public UpdateableTreeSet(Comparator<JobsWithCount> comparator) {
        super(comparator);
    }

    public void updateByMinusCount(JobsWithCount jobWithCount, Character job) {
        if (remove(jobWithCount)) {
            jobWithCount.jobs.remove(job);
            if (jobWithCount.count > 1) {
                JobsWithCount remainingJob = new JobsWithCount(jobWithCount.count - 1, new ArrayList<Character>())
                        .withJob(job);

                if (!add(remainingJob)) {
                    Iterator<JobsWithCount> it = iterator();
                    while (it.hasNext()) {
                        JobsWithCount current = it.next();
                        if (current.count == remainingJob.count) {
                            remove(current);
                            current.jobs.add(job);
                            add(current);
                            break;
                        }
                    }
                }
            }

            if (jobWithCount.jobs.size() > 0) {
                add(jobWithCount);
            }
        }
    }
}
