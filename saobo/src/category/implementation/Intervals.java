package category.implementation;

import interview.utils.exceptions.OverlapIntervalsException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

class Interval {

    int end;

    int start;

    public Interval() {
        start = 0;
        end = 0;
    }

    public Interval(int s, int e) {
        this.start = s;
        this.end = e;
    }

    @Override
    public String toString() {
        return "" + start + ":" + end;
    }

}

public class Intervals {

    public static List<Interval> insertWhenNoCross(List<Interval> intervals, Interval newInterval) {
        if (intervals == null) {
            return Arrays.asList(newInterval);
        }

        int start = 0;
        int end = intervals.size() - 1;

        if (newInterval.end < intervals.get(start).start) {
            intervals.add(0, newInterval);
            return intervals;
        }

        if (newInterval.start > intervals.get(end).end) {
            intervals.add(newInterval);
            return intervals;
        }

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (intervals.get(mid).start < newInterval.start) {
                start = mid;
            } else if (intervals.get(mid).start > newInterval.start) {
                end = mid;
            } else {
                throw new OverlapIntervalsException("overlapped!");
            }
        }

        if (intervals.get(start).end >= newInterval.start || intervals.get(end).start <= newInterval.end) {
            throw new OverlapIntervalsException("overlapped!");
        } else {
            intervals.add(end, newInterval);
        }

        return intervals;
    }

    private static void printAll(List<Interval> intervals) {
        for (Interval interval : intervals) {
            System.out.print(interval.start + "-" + interval.end + " ");
        }
        System.out.println();

    }

    public static List<Interval> remove(List<Interval> intervals, Interval intervalToRemove) {
        List<Interval> result = new ArrayList<Interval>();

        if (intervals == null) {
            return result;
        }

        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < intervalToRemove.start) {
            result.add(intervals.get(i++));
        }

        while (i < intervals.size() && intervals.get(i).end <= intervalToRemove.end) {
            i++;
        }

        if (i < intervals.size()) {
            Interval overlappedInterval = new Interval(intervalToRemove.end + 1, intervals.get(i++).end);
            result.add(overlappedInterval);
        }

        while (i < intervals.size()) {
            result.add(intervals.get(i));
        }

        return result;
    }

    private List<Interval> testIntervalLists;

    private void assertListEqual(List<Interval> list1, List<Interval> list2) {
        Assert.assertEquals(list1.size(), list2.size());

        Iterator<Interval> iter1 = list1.iterator();
        Iterator<Interval> iter2 = list2.iterator();
        while (iter1.hasNext()) {
            Interval i1 = iter1.next();
            Interval i2 = iter2.next();

            Assert.assertEquals(i1.start, i1.start);
            Assert.assertEquals(i1.end, i2.end);
        }

    }

    /**
     * [Leetcode 57] https://leetcode.com/problems/insert-interval/
     * 
     * <pre>
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     * You may assume that the intervals were initially sorted according to their start times.
     * 
     * Example 1:
     * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
     * 
     * Example 2:
     * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
     * 
     * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
     * </pre>
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public ArrayList<Interval> insert(List<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        if (intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }

        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            result.add(intervals.get(i++));
        }

        if (i < intervals.size()) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
        }

        result.add(newInterval);

        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval.end = Math.max(newInterval.end, intervals.get(i++).end);
        }

        while (i < intervals.size()) {
            result.add(intervals.get(i++));
        }

        return result;
    }

    /**
     * [Leetcode 56] https://leetcode.com/problems/merge-intervals/
     *
     * <pre>
     * Given a collection of intervals, merge all overlapping intervals. For example, Given [1,3],[2,6],[8,10],[15,18],
     * return [1,6],[8,10],[15,18].
     * </pre>
     *
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();

        if (intervals == null || intervals.isEmpty()) {
            return result;
        }

        Collections.sort(intervals, (interval1, interval2) -> (Integer.compare(interval1.start, interval2.start)));
        result.add(intervals.get(0));

        for (int i = 1; i < intervals.size(); i++) {
            Interval last = result.get(result.size() - 1);
            Interval current = intervals.get(i);
            if (current.start <= last.end) {
                result.remove(result.size() - 1);
                Interval newInterval = new Interval(last.start, Math.max(last.end, current.end));
                result.add(newInterval);
            } else {
                result.add(current);
            }
        }

        return result;
    }

    @Before
    public void setup() {
        Interval interval1 = new Interval(3, 5);
        Interval interval2 = new Interval(7, 10);
        Interval interval3 = new Interval(11, 13);
        Interval interval4 = new Interval(15, 20);
        Interval interval5 = new Interval(23, 30);

        testIntervalLists = new ArrayList<Interval>();
        testIntervalLists.add(interval1);
        testIntervalLists.add(interval2);
        testIntervalLists.add(interval3);
        testIntervalLists.add(interval4);
        testIntervalLists.add(interval5);
    }

    @Test
    public void testAddToHead() {
        List<Interval> result = insertWhenNoCross(testIntervalLists, new Interval(1, 2));
        testIntervalLists.add(0, new Interval(1, 2));
        assertListEqual(result, testIntervalLists);
    }

    @Test(expected = OverlapIntervalsException.class)
    public void testAddToHeadWithOverlap() {
        insertWhenNoCross(testIntervalLists, new Interval(0, 3));
    }

    @Test
    public void testAddToMiddle() {
        List<Interval> result = insertWhenNoCross(testIntervalLists, new Interval(14, 14));
        testIntervalLists.add(3, new Interval(14, 14));
        assertListEqual(result, testIntervalLists);
    }

    @Test(expected = OverlapIntervalsException.class)
    public void testAddToMiddleWithOverlap() {
        insertWhenNoCross(testIntervalLists, new Interval(14, 17));
    }

    @Test
    public void testAddToTail() {
        List<Interval> result = insertWhenNoCross(testIntervalLists, new Interval(33, 100));
        testIntervalLists.add(new Interval(33, 100));
        assertListEqual(result, testIntervalLists);
    }

    @Test(expected = OverlapIntervalsException.class)
    public void testAddToTailWithOverlap() {
        insertWhenNoCross(testIntervalLists, new Interval(29, 31));
    }

    @Test
    public void testInsertNewIntervalConnectTwoIntervals() {
        Interval toInsert = new Interval(5, 23);
        List<Interval> returned = insert(testIntervalLists, toInsert);

        Interval interval1 = new Interval(3, 30);
        List<Interval> expectedList = Arrays.asList(interval1);

        assertListEqual(expectedList, returned);
    }

    @Test
    public void testInsertNewIntervalMeetFistStart() {
        Interval toInsert = new Interval(1, 3);
        List<Interval> returned = insert(testIntervalLists, toInsert);

        Interval interval1 = new Interval(1, 5);
        Interval interval2 = new Interval(7, 10);
        Interval interval3 = new Interval(11, 13);
        Interval interval4 = new Interval(15, 20);
        Interval interval5 = new Interval(23, 30);

        List<Interval> expectedList = Arrays.asList(interval1, interval2, interval3, interval4, interval5);
        assertListEqual(expectedList, returned);
    }

    @Test
    public void testInsertNewIntervalMeetLastEnd() {
        Interval toInsert = new Interval(30, 33);
        List<Interval> returned = insert(testIntervalLists, toInsert);

        Interval interval1 = new Interval(3, 5);
        Interval interval2 = new Interval(7, 10);
        Interval interval3 = new Interval(11, 13);
        Interval interval4 = new Interval(15, 20);
        Interval interval5 = new Interval(23, 33);
        List<Interval> expectedList = Arrays.asList(interval1, interval2, interval3, interval4, interval5);

        printAll(expectedList);
        printAll(returned);
        System.out.println("~~~~~~~~~~");

        assertListEqual(expectedList, returned);
    }

    @Test
    public void testInsertNewIntervalStartEndFirst() {
        Interval toInsert = new Interval(0, 1);
        List<Interval> returned = insert(testIntervalLists, toInsert);

        List<Interval> expectedList = new ArrayList<Interval>();
        expectedList.add(toInsert);
        expectedList.addAll(testIntervalLists);

        assertListEqual(expectedList, returned);
    }

    @Test
    public void testInsertNewIntervalStartEndLast() {
        Interval toInsert = new Interval(31, 40);
        List<Interval> returned = insert(testIntervalLists, toInsert);

        List<Interval> expectedList = new ArrayList<Interval>();
        expectedList.addAll(testIntervalLists);
        expectedList.add(toInsert);

        printAll(expectedList);
        printAll(returned);
        System.out.println("~~~~~~~~~~");

        assertListEqual(expectedList, returned);

    }

    @Test
    public void testInsertNewIntervalStartEndMiddle1() {
        Interval toInsert = new Interval(7, 20);
        List<Interval> returned = insert(testIntervalLists, toInsert);

        Interval interval1 = new Interval(3, 5);
        Interval interval2 = new Interval(7, 20);
        Interval interval3 = new Interval(23, 30);
        List<Interval> expectedList = Arrays.asList(interval1, interval2, interval3);

        assertListEqual(expectedList, returned);

    }

    @Test
    public void testInsertNewIntervalStartEndMiddle2() {
        Interval toInsert = new Interval(6, 21);
        List<Interval> returned = insert(testIntervalLists, toInsert);

        List<Interval> expectedList = new ArrayList<Interval>();
        Interval interval1 = new Interval(3, 5);
        Interval interval2 = new Interval(6, 21);
        Interval interval3 = new Interval(23, 30);
        expectedList.add(interval1);
        expectedList.add(interval2);
        expectedList.add(interval3);

        assertListEqual(expectedList, returned);
    }

    @Test
    public void testInsertNewIntervalToTail() {
        Interval toInsert = new Interval(33, 40);
        List<Interval> returned = insert(testIntervalLists, toInsert);

        List<Interval> expectedList = new ArrayList<Interval>(testIntervalLists);
        expectedList.add(toInsert);

        assertListEqual(expectedList, returned);
    }

    @Test
    public void testRemoveAll() {
        List<Interval> result = remove(testIntervalLists, new Interval(1, 100));
        assertListEqual(result, new ArrayList<Interval>());
    }

    @Test
    public void testRemoveAll2() {
        List<Interval> result = remove(testIntervalLists, new Interval(3, 30));
        assertListEqual(result, new ArrayList<Interval>());
    }

    @Test
    public void testRemoveHead() {
        Interval interval = new Interval(23, 30);

        List<Interval> expectedList = Arrays.asList(interval);

        List<Interval> result = remove(testIntervalLists, new Interval(3, 20));

        assertListEqual(result, expectedList);
    }

    @Test
    public void testRemoveMiddle() {
        Interval interval1 = new Interval(3, 5);
        Interval interval2 = new Interval(23, 30);

        List<Interval> expectedList = Arrays.asList(interval1, interval2);

        List<Interval> result = remove(testIntervalLists, new Interval(7, 20));

        assertListEqual(result, expectedList);
    }

    @Test
    public void testRemoveTail() {
        Interval interval1 = new Interval(3, 5);
        Interval interval2 = new Interval(7, 10);
        Interval interval3 = new Interval(11, 13);
        Interval interval4 = new Interval(15, 20);

        List<Interval> expectedList = Arrays.asList(interval1, interval2, interval3, interval4);

        List<Interval> result = remove(testIntervalLists, new Interval(21, 30));

        assertListEqual(result, expectedList);
    }
}
