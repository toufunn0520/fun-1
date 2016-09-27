package category.search.bfs.floodfill;

public class NumberOfIslands {

    private void findIsland(char[][] map, int[][] visit, int i, int j) {
        visit[i][j] = 1;
        if (i - 1 >= 0 && map[i - 1][j] == '1' && visit[i - 1][j] == 0) {
            visit[i - 1][j] = 1;
            findIsland(map, visit, i - 1, j);
        }
        if (i + 1 < map.length && map[i + 1][j] == '1' && visit[i + 1][j] == 0) {
            visit[i + 1][j] = 1;
            findIsland(map, visit, i + 1, j);
        }
        if (j - 1 >= 0 && map[i][j - 1] == '1' && visit[i][j - 1] == 0) {
            visit[i][j - 1] = 1;
            findIsland(map, visit, i, j - 1);
        }
        if (j + 1 < map[0].length && map[i][j + 1] == '1' && visit[i][j + 1] == 0) {
            visit[i][j + 1] = 1;
            findIsland(map, visit, i, j + 1);
        }
    }

    /**
     * [Leetcode 200] https://leetcode.com/problems/number-of-islands/
     *
     * <pre>
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
     *
     * Example 1:
     *
     * 11110
     * 11010
     * 11000
     * 00000
     * Answer: 1
     *
     * Example 2:
     *
     * 11000
     * 11000
     * 00100
     * 00011
     * Answer: 3
     * </pre>
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        char[][] map = new char[m][n];
        int[][] visit = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                map[i][j] = grid[i][j];

        int count = 0;

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && visit[i][j] == 0) {
                    count++;
                    findIsland(map, visit, i, j);
                }
            }

        return count;
    }
}
