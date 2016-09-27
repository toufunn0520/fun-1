package category.search.bfs;

import interview.utils.Point;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * [Snapchat]
 * Consider a grid where all the points are represented by integers.
 *
 * .........................................
 * ...(-2,2)  (-1,2)  (0,2)  (1,2)  (2,2)....
 * ...(-2,1)  (-1,1)  (0,1)  (1,1)  (2,1)....
 * ...(-2,0)  (-1,0)  (0,0)  (1,0)  (2,0)...
 * ...(-2,-1) (-1,-1) (0,-1) (1,-1) (2,-1)...
 * ...(-2,-2) (-1,-2) (0,-2) (1,-2) (2,-2)...
 * ..........................................
 *
 * k-Snap point: A point whose digits sum up to less than or equal to k. In this
 * question, we ignore all the signs in the number.  For exxample, (1, 0) is a 1-snap point, (0, 10) is a 1-snap point, and (-100, 0) is also a 1-snap point; however (11, 0) is not a 1-snap point.. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
 *
 * Question 1: Implement the following function. visit 1point3acres.com for more.
 * boolean isSnapPoint(Point p, int k)
 *
 * Returns true if p is a k-snap point, and false otherwise.
 *
 * Reachable k-snap point: A k-snap point is a reachable k-snap point if there is a path from (0,0) to that point, where the path only consists of k-snap points.. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
 *
 * Question 2: Given k, return all the reachable k-snap points.
 * </pre>
 **/
public class SnapPoint {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    private Point[][] grid;

    public SnapPoint(Point[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            throw new RuntimeException();
        }

        this.grid = grid;
    }

    public boolean isSnapPoint(Point p, int k) {
        if (p == null) {
            return false;
        }

        int x = Math.abs(p.x);
        int y = Math.abs(p.y);

        int value = 0;

        while (x > 0) {
            value += y % 10;
            y /= 10;
        }

        while (y > 0) {
            value += x % 10;
            x /= 10;
        }

        return value <= k;
    }

    public Set<Point> reachableSnapPoints(int k) {
        Set<Point> points = new HashSet<Point>();

        if (k < 0) {
            return points;
        }

        searchReachablePoints(new Point(0, 0), k, points);

        return points;
    }

    private void searchReachablePoints(Point point, int k, Set<Point> points) {
        if (!isSnapPoint(point, k)) {
            return;
        }

        points.add(point);

        Point left = new Point(point.y, point.x - 1);
        Point right = new Point(point.y, point.x + 1);
        Point up = new Point(point.y + 1, point.x);
        Point down = new Point(point.y - 1, point.x);

        if (!points.contains(left)) {
            searchReachablePoints(left, k, points);
        }

        if (!points.contains(right)) {
            searchReachablePoints(right, k, points);
        }

        if (!points.contains(up)) {
            searchReachablePoints(up, k, points);
        }

        if (!points.contains(down)) {
            searchReachablePoints(down, k, points);
        }
    }
}
