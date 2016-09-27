package category.greedy;

import interview.utils.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MeetingRoom {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * [Leetcode 253] https://leetcode.com/problems/meeting-rooms-ii/
     * 
     * <pre>
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
     * find the minimum number of conference rooms required.
     * 
     * For example,
     * Given [[0, 30],[5, 10],[15, 20]],
     * return 2.
     * </pre>
     *
     * @param meetings
     * @return
     */
    public int minMeetingRooms(Interval[] meetings) {
        if (meetings == null) {
            return 0;
        }

        List<Integer> moments = new ArrayList<Integer>();
        for (Interval i : meetings) {
            moments.add(i.start);
            moments.add(-1 * i.end);
        }

        Collections.sort(moments, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                if (Math.abs(o1) == Math.abs(o2)) {
                    return Integer.compare(o1, o2);
                } else {
                    return Integer.compare(Math.abs(o1), Math.abs(o2));
                }
            }

        });

        int minNumberOfRoom = 0;
        int count = 0;
        for (Integer moment : moments) {
            if (moment > 0) {
                count++;
                minNumberOfRoom = Math.max(minNumberOfRoom, count);
            } else {
                count--;
            }
        }

        return minNumberOfRoom;
    }
}
