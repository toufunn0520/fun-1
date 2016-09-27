package category.search.bfs;

import interview.utils.Point;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given a matrix contains 1 or 0. start from the left up corner and you need to move up, down, left or right each step
 * if the next grid is not 1, calculate how many steps needs to be the right down corner.
 */
public class StepsToDestination {

    private boolean checkRange(Point p, int[][] grid) {
        return p.x >= 0 && p.y >= 0 && p.y < grid.length && p.x < grid[0].length;
    }

    public int getSteps(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        if (grid[0][0] == 1) {
            return Integer.MAX_VALUE;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(new Point(0, 0));

        boolean[][] visited = new boolean[rows][cols];

        int step = 0;
        while (!queue.isEmpty()) {
            int countOfThisStep = queue.size();

            for (int i = 0; i < countOfThisStep; i++) {
                Point current = queue.poll();
                int x = current.x;
                int y = current.y;
                visited[y][x] = true;
                if (y == rows - 1 && x == cols - 1) {
                    return step;
                }

                Point[] neighbors = { new Point(y, x - 1), new Point(y, x + 1), new Point(y + 1, x),
                        new Point(y - 1, x) };

                for (Point neighbor : neighbors) {
                    if (isValid(neighbor, grid, visited)) {
                        queue.offer(neighbor);
                    }
                }
            }

            step++;
        }

        return Integer.MAX_VALUE;
    }

    private boolean isValid(Point p, int[][] grid, boolean[][] visited) {
        return checkRange(p, grid) && grid[p.y][p.x] != 1 && !visited[p.y][p.x];
    }

    @Test
    public void testWithNoObstacles() {
        int[][] grid1 = { { 0 } };
        int[][] grid2 = { { 0, 0 } };
        int[][] grid3 = { { 0, 0 }, { 0, 0 } };
        int[][] grid4 = { { 0, 0, 0 }, { 0, 0, 0 } };
        int[][] grid5 = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
        Assert.assertEquals(0, new StepsToDestination().getSteps(grid1));
        Assert.assertEquals(1, new StepsToDestination().getSteps(grid2));
        Assert.assertEquals(2, new StepsToDestination().getSteps(grid3));
        Assert.assertEquals(3, new StepsToDestination().getSteps(grid4));
        Assert.assertEquals(4, new StepsToDestination().getSteps(grid5));
    }

    @Test
    public void testWithObstacles() {
        int[][] grid1 = { { 0, 1, 0, 0, 0 }, { 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 1, 0 } };
        int[][] grid2 = { { 0, 1, 0, 0, 0 }, { 0, 1, 0, 1, 0 }, { 0, 1, 0, 0, 0 }, { 0, 0, 0, 1, 0 } };
        Assert.assertEquals(13, new StepsToDestination().getSteps(grid1));
        Assert.assertEquals(9, new StepsToDestination().getSteps(grid2));
    }

    @Test
    public void testWithObstaclesOutOfReach() {
        int[][] grid1 = { { 1 } };
        int[][] grid2 = { { 0, 1, 0 }, { 0, 1, 0 }, { 0, 1, 0 } };
        int[][] grid3 = { { 0, 0, 1 }, { 0, 1, 0 }, { 1, 0, 0 } };
        Assert.assertEquals(Integer.MAX_VALUE, new StepsToDestination().getSteps(grid1));
        Assert.assertEquals(Integer.MAX_VALUE, new StepsToDestination().getSteps(grid2));
        Assert.assertEquals(Integer.MAX_VALUE, new StepsToDestination().getSteps(grid3));
    }
}
