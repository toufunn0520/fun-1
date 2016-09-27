package category.search.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 *  Given: An array of strings where L indicates land and W indicates water,
 *  and a coordinate marking a starting point in the middle of the ocean.
 * 
 *  Challenge: Find and mark the ocean in the map by changing appropriate Ws to Os.
 *  An ocean coordinate is defined to be the initial coordinate if a W, and
 *  any coordinate directly adjacent to any other ocean coordinate.
 * </pre>
 */
public class FillOcean {

    private static class Point {

        int x;

        int y;

        Point(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * @param map
     * @param row
     * @param column
     */
    public static void findOcean(String[] map, int row, int column) {
        if (map == null || map.length == 0) {
            return;
        }

        int rowLength = map.length;
        int colLength = map[0].length();

        char[][] grid = new char[rowLength][colLength];
        for (int i = 0; i < rowLength; i++) {
            grid[i] = map[i].toCharArray();
        }

        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(new Point(row, column));

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            grid[current.y][current.x] = 'O';

            if (current.y + 1 < rowLength && grid[current.y + 1][current.x] == 'W') {
                queue.add(new Point(current.y + 1, current.x));
            }

            if (current.y - 1 >= 0 && grid[current.y - 1][current.x] == 'W') {
                queue.add(new Point(current.y - 1, current.x));
            }

            if (current.x - 1 >= 0 && grid[current.y][current.x - 1] == 'W') {
                queue.add(new Point(current.y, current.x - 1));
            }

            if (current.x + 1 < colLength && grid[current.y][current.x + 1] == 'W') {
                queue.add(new Point(current.y, current.x + 1));
            }
        }

        for (int i = 0; i < rowLength; i++) {
            map[i] = new String(grid[i]);
        }
    }

    public static void main(String[] args) {
        String[] map = { "WWWLLLW", "WWLLLWW", "WLLLLWW" };
        findOcean(map, 0, 1);

        for (int i = 0; i < map.length; i++) {
            System.out.println(map[i]);
        }
    }

}
