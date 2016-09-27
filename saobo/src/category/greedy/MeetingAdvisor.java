package category.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

;

/**
 * Twitter onsite: Given a array of pairs where each pair contains the start and end time of a meeting (as in int),
 * Determine if a single person can attend all the meetings For example: Input array { pair(1,4), pair(4, 5), pair(3,4),
 * pair(2,3) } Output: false Follow up: determine the minimum number of meeting rooms needed to hold all the meetings.
 * Input array { pair(1, 4), pair(2,3), pair(3,4), pair(4,5) } Output: 2 determine the max time period for meetings
 * 
 * @author boyi
 */
public class MeetingAdvisor {

    public static void main(String[] args) {
        List<TimePeriod> timePeriods = new ArrayList<TimePeriod>();

        MeetingAdvisor meetingAdvisor = new MeetingAdvisor(timePeriods);
        timePeriods.add(meetingAdvisor.new TimePeriod(3, 5));
        timePeriods.add(meetingAdvisor.new TimePeriod(3, 4));
        timePeriods.add(meetingAdvisor.new TimePeriod(1, 3));
        timePeriods.add(meetingAdvisor.new TimePeriod(5, 11));
        timePeriods.add(meetingAdvisor.new TimePeriod(2, 7));

        System.out.println(meetingAdvisor.MinRooms());
        System.out.println(meetingAdvisor.maxMeetingTime());
    }

    public MeetingAdvisor(List<TimePeriod> timePeriods) {

        this.timePeriods = timePeriods;
    }

    private List<TimePeriod> timePeriods;

    public static final Comparator<Integer> TIMES_COMPARATOR = new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            if (Math.abs(o1) > Math.abs(o2)) {
                return 1;
            } else if (Math.abs(o1) < Math.abs(o2)) {
                return -1;
            } else {
                return 0;
            }
        }

    };

    public int MinRooms() {
        List<Integer> times = new ArrayList<Integer>(timePeriods.size() * 2);
        for (TimePeriod tp : timePeriods) {
            times.add(tp.startTime);
            times.add(-1 * tp.endTime);
        }

        Collections.sort(times, TIMES_COMPARATOR);

        int minRoom = 0;
        int curRoom = 0;
        for (Integer time : times) {
            if (time > 0) {
                minRoom = Math.max(minRoom, ++curRoom);
            } else if (time < 0) {
                curRoom--;
            }
        }

        return minRoom;
    }

    public Boolean attendAll() {
        Collections.sort(timePeriods);

        int lastStartTime = -1;
        int lastEndTime = -1;
        for (TimePeriod tp : timePeriods) {
            lastStartTime = tp.startTime;
            if (lastStartTime < lastEndTime) {
                return false;
            } else {
                lastEndTime = tp.endTime;
            }
        }
        return true;
    }

    public int maxMeetingTime() {
        Collections.sort(timePeriods);

        int maxTime = timePeriods.get(0).endTime - timePeriods.get(0).startTime;
        for (int i = 1, j = 0; i < timePeriods.size(); i++) {
            if (timePeriods.get(i).startTime > timePeriods.get(j).endTime) {
                // it means there is no overlap
                maxTime += timePeriods.get(i).endTime - timePeriods.get(i).startTime;
                j = i;
            } else {
                if (timePeriods.get(i).endTime <= timePeriods.get(j).endTime) {
                    // even overlap, the ith time period is included in jth time period.
                    continue;
                } else {
                    maxTime += timePeriods.get(i).endTime - timePeriods.get(j).endTime;
                    j = i;
                }
            }
        }

        return maxTime;
    }

    public class TimePeriod implements Comparable<TimePeriod> {

        int startTime;

        int endTime;

        public TimePeriod(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(TimePeriod o) {
            if (this.startTime > o.startTime) {
                return 1;
            } else if (this.startTime < o.startTime) {
                return -1;
            } else {
                if (this.endTime > o.endTime) {
                    return 1;
                } else if (this.endTime < o.endTime) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

    }

}
