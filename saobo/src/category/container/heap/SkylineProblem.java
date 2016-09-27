package category.container.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * [Leetcode 218] https://leetcode.com/problems/the-skyline-problem/
 */
public class SkylineProblem {

    private class Point implements Comparable<Point> {

        public int h;// height of the building

        public boolean isLeft; // this point is left or right point of building

        public int pos;// coordinate of the point

        public Point(int pos, int h, boolean left) {
            this.pos = pos;
            this.h = h;
            this.isLeft = left;
        }

        @Override
        public int compareTo(Point other) {
            return pos - other.pos;
        }
    }

    private List<int[]> dedup(List<int[]> input) {
        List<int[]> result = new ArrayList<int[]>();
        for (int i = 0; i < input.size(); i++) {
            if (i == 0 || input.get(i)[1] != input.get(i - 1)[1]) {
                result.add(input.get(i));
            }
        }
        return result;
    }

    public List<int[]> getSkyline(int[][] buildings) {
        Set<Integer> resultSet = new HashSet<Integer>();

        List<int[]> result = new ArrayList<int[]>();
        if (buildings.length == 0) {
            return result;
        }
        List<Point> points = new ArrayList<Point>();
        for (int i = 0; i < buildings.length; i++) {
            points.add(new Point(buildings[i][0], buildings[i][2], true));
            points.add(new Point(buildings[i][1], buildings[i][2], false));
        }
        // sort all building left and right points
        Collections.sort(points);
        // max heap
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(buildings.length, (int1, int2) -> Integer.compare(int2,
                int1));
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            if (p.isLeft) {
                pq.offer(p.h);
            } else {
                pq.remove(p.h);
            }
            // needs special handling for points at same pos. for example input is [[2,4,5],[2,4,6],[2,4,7]]
            if (i == points.size() - 1 || p.pos != points.get(i + 1).pos) {
                int[] keyPoint = new int[] { p.pos, pq.isEmpty() ? 0 : pq.peek() };
                result.add(keyPoint);
                if (resultSet.add(keyPoint[1])) {
                    resultSet.add(keyPoint[1]);
                    result.add(keyPoint);
                }
            }

        }
        return dedup(result);
    }
}
