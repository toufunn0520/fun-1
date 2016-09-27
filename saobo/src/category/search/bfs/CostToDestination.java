package category.search.bfs;

import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

public class CostToDestination {

    public static void main(String[] args) {
        int[][] matrix = { { 1, 3, 8 }, { 8, 1, 2 }, { 1, 2, 8 } };

        System.out.println(new CostToDestination().getMinCost2(matrix));
    }

    /**
     * [Google] Given a matrix of cost, start from left-up corner and end with right-down. What is the min cost. You can
     * go four directions if there is a grid next to it.
     *
     * @param cost
     * @return
     */
    public int getMinCost(int[][] cost) {
        if (cost == null || cost.length == 0 || cost[0] == null || cost[0].length == 0) {
            return 0;
        }

        int rows = cost.length;
        int cols = cost[0].length;

        PriorityQueue<MatrixPoint> queue = new PriorityQueue<MatrixPoint>((point1, point2) -> (Integer.compare(
                point1.cost, point2.cost)));
        queue.add(new MatrixPoint(0, 0, cost[0][0]));
        boolean[][] visited = new boolean[rows][cols];

        while (!queue.isEmpty()) {
            MatrixPoint current = queue.poll();

            if (current.y == rows - 1 && current.x == cols - 1) {
                return current.cost;
            }

            visited[current.y][current.x] = true;
            pushNeighbour(cost, current, visited, queue);
        }

        return Integer.MAX_VALUE;
    }

    /**
     * [Tableau] Start from any row of the first column to any row of the last column. What is the minimal cost
     *
     * @param cost
     * @return
     */
    public int getMinCost2(int[][] cost) {
        if (cost == null || cost.length == 0 || cost[0] == null || cost[0].length == 0) {
            return 0;
        }

        int rows = cost.length;
        int cols = cost[0].length;

        PriorityQueue<MatrixPoint> queue = new PriorityQueue<MatrixPoint>((point1, point2) -> (Integer.compare(
                point1.cost, point2.cost)));

        for (int i = 0; i < rows; i++) {
            queue.add(new MatrixPoint(0, i, cost[i][0]));
        }

        boolean[][] visited = new boolean[rows][cols];

        int minCost = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            MatrixPoint current = queue.poll();

            if (current.x == cols - 1) {
                minCost = Math.min(minCost, current.cost);
                continue;
            }

            visited[current.y][current.x] = true;
            pushNeighbour(cost, current, visited, queue);
        }

        return minCost;
    }

    private void pushNeighbour(int[][] matrix, MatrixPoint matrixPoint, boolean[][] visited,
            PriorityQueue<MatrixPoint> queue) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int y = matrixPoint.y;
        int x = matrixPoint.x;

        if (y - 1 >= 0 && !visited[y - 1][x]) {
            queue.offer(new MatrixPoint(x, y - 1, matrixPoint.cost + matrix[y - 1][x]));
        }

        if (y + 1 < rows && !visited[y + 1][x]) {
            queue.offer(new MatrixPoint(x, y + 1, matrixPoint.cost + matrix[y + 1][x]));
        }

        if (x + 1 < cols && !visited[y][x + 1]) {
            queue.offer(new MatrixPoint(x + 1, y, matrixPoint.cost + matrix[y][x + 1]));
        }

        if (x - 1 >= 0 && !visited[y][x - 1]) {
            queue.offer(new MatrixPoint(x - 1, y, matrixPoint.cost + matrix[y][x - 1]));
        }
    }

    @Test
    public void testGetMinCost1WithDifferentCases() {
        CostToDestination costToDestination = new CostToDestination();
        int[][] cost1 = { { 10 } };
        int[][] cost2 = { { 1, 1 }, { 1, 1 } };
        int[][] cost3 = { { 1, 2 }, { 3, 4 } };
        int[][] cost4 = { { 1, 3, 8 }, { 2, 5, 6 } };
        int[][] cost5 = { { 1, 3, 8 }, { 2, 5, 6 }, { -10, 2, 3 } };

        Assert.assertEquals(10, costToDestination.getMinCost(cost1));
        Assert.assertEquals(3, costToDestination.getMinCost(cost2));
        Assert.assertEquals(7, costToDestination.getMinCost(cost3));
        Assert.assertEquals(14, costToDestination.getMinCost(cost4));
        Assert.assertEquals(-2, costToDestination.getMinCost(cost5));
    }

    @Test
    public void testGetMinCost2WithDifferentCases() {
        CostToDestination costToDestination = new CostToDestination();
        int[][] cost1 = { { 10 } };
        int[][] cost2 = { { 1, 1 }, { 1, 1 } };
        int[][] cost3 = { { 1, 2 }, { 3, 4 } };
        int[][] cost4 = { { 1, 3, 8 }, { 8, 5, 2 } };
        int[][] cost5 = { { 1, 3, 8 }, { 8, 1, 2 }, { 1, 2, 8 } };

        Assert.assertEquals(10, costToDestination.getMinCost2(cost1));
        Assert.assertEquals(2, costToDestination.getMinCost2(cost2));
        Assert.assertEquals(3, costToDestination.getMinCost2(cost3));
        Assert.assertEquals(11, costToDestination.getMinCost2(cost4));
        Assert.assertEquals(6, costToDestination.getMinCost2(cost5));
    }

}

class MatrixPoint {

    int cost;

    int x;

    int y;

    public MatrixPoint(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return " y:" + y + "x:" + x + " cost:" + cost;
    }

}
