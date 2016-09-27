package category.search.bfs;

import interview.utils.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindMinManhattanDistance {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    private void bfsFillCost(int employeeIndex, int currentCost, int[][] matrix, int[][][] cost, Queue<Point> queue) {
        int rows = cost[0].length;
        int cols = cost[0][0].length;

        while (!queue.isEmpty()) {
            int currentCount = queue.size();
            for (int i = 0; i < currentCount; i++) {
                Point currentPoint = queue.poll();
                int x = currentPoint.x;
                int y = currentPoint.y;

                if (currentCost == 0 || currentCost < cost[employeeIndex][y][x]) {
                    cost[employeeIndex][y][x] = currentCost;

                    if (x + 1 < cols && matrix[y][x + 1] != 2 && cost[employeeIndex][y][x + 1] < currentCost + 1) {
                        queue.add(new Point(y, x + 1));
                    }

                    if (x > 0 && matrix[y][x - 1] != 2 && cost[employeeIndex][y][x - 1] < currentCost + 1) {
                        queue.add(new Point(y, x - 1));
                    }

                    if (y > 0 && matrix[y - 1][x] != 2 && cost[employeeIndex][y - 1][x] < currentCost + 1) {
                        queue.add(new Point(y - 1, x));
                    }

                    if (y + 1 < rows && matrix[y + 1][x] != 2 && cost[employeeIndex][y + 1][x] < currentCost + 1) {
                        queue.add(new Point(y + 1, x));
                    }
                }
            }

            currentCost++;
        }
    }

    /**
     * @param matrix
     *            0 means empty 1 means employee 2 means blocker
     * @return
     */
    public Point findBestWaterPoint(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return null;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        List<Point> list = new ArrayList<Point>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 2) {
                    list.add(new Point(i, j));
                }
            }
        }

        int[][][] cost = new int[list.size()][rows][cols];

        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            Point current = list.get(i);
            Queue<Point> currentQueue = new LinkedList<Point>();
            currentQueue.offer(current);

            bfsFillCost(count++, 0, matrix, cost, currentQueue);
        }

        int bestPointIndex = getPointIndexWithMinimalCost(cost);
        return list.get(bestPointIndex);
    }

    private Integer getPointIndexWithMinimalCost(int[][][] cost) {
        int count = cost.length;
        int rows = cost[0].length;
        int cols = cost[0][0].length;

        int minCost = 0;
        Integer bestPointIndex = null;

        for (int i = 0; i < count; i++) {
            int currentCost = 0;
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    currentCost += cost[i][j][k];
                }
            }

            if (bestPointIndex == null || currentCost < minCost) {
                bestPointIndex = i;
            }
        }

        return bestPointIndex;
    }
}
