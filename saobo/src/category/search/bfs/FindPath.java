package category.search.bfs;

import interview.utils.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FindPath {

    /**
     * [snap*chat] M*N的地图上0表示可以走，1表示不能走，求从左上角到右下角的最短路径
     *
     * @param grid
     * @return
     */
    public static List<Point> findMinPath(int[][] grid) {
        List<Point> result = new ArrayList<Point>();

        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return result;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        Map<Point, Point> point2Prepoint = new HashMap<Point, Point>();

        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(new Point(0, 0));
        grid[0][0] = 1;

        Point endPoint = new Point(rows - 1, cols - 1);

        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                Point current = queue.poll();
                if (current.equals(endPoint)) {
                    break;
                }

                int x = current.x;
                int y = current.y;

                if (x > 0 && grid[y][x - 1] == 0) {
                    grid[y][x - 1] = 1;
                    point2Prepoint.put(new Point(y, x - 1), current);
                    queue.offer(new Point(y, x - 1));
                }

                if (y > 0 && grid[y - 1][x] == 0) {
                    grid[y - 1][x] = 1;
                    point2Prepoint.put(new Point(y - 1, x), current);
                    queue.offer(new Point(y - 1, x));
                }

                if (x < cols - 1 && grid[y][x + 1] == 0) {
                    grid[y][x + 1] = 1;
                    point2Prepoint.put(new Point(y, x + 1), current);
                    queue.offer(new Point(y, x + 1));
                }

                if (y < rows - 1 && grid[y + 1][x] == 0) {
                    grid[y + 1][x] = 1;
                    point2Prepoint.put(new Point(y + 1, x), current);
                    queue.offer(new Point(y + 1, x));
                }
            }
        }

        Point current = endPoint;
        if (point2Prepoint.containsKey(current)) {
            while (point2Prepoint.containsKey(current)) {
                result.add(0, current);
                current = point2Prepoint.get(current);
            }
            result.add(0, new Point(0, 0));
            return result;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        int[][] grid = { { 0, 1, 0 }, { 0, 0, 0 }, { 0, 1, 0 } };

        List<Point> result = findMinPath(grid);

        for (Point p : result) {
            System.out.println(p);
        }
    }
}
