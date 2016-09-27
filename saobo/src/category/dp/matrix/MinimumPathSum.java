package category.dp.matrix;

public class MinimumPathSum {

    /**
     * [Leetcode 64] https://leetcode.com/problems/minimum-path-sum/
     * 
     * <pre>
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes
     * the sum of all numbers along its path. Note: You can only move either down or right at any point in time.
     * </pre>
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] pathSum = new int[rows][cols];

        pathSum[0][0] = grid[0][0];

        for (int i = 1; i < rows; i++) {
            pathSum[i][0] = pathSum[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < cols; i++) {
            pathSum[0][i] = pathSum[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                pathSum[i][j] = Math.min(pathSum[i - 1][j], pathSum[i][j - 1]) + grid[i][j];
            }
        }

        return pathSum[rows - 1][cols - 1];
    }
}
