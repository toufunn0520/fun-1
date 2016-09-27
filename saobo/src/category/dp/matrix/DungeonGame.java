package category.dp.matrix;

public class DungeonGame {

    public static void main(String[] args) {
        int[][] d = { { 1, -3, 3 }, { 0, -2, 0 }, { -3, -3, -3 } };
        System.out.println(new DungeonGame().calculateMinimumHP(d));
    }

    /**
     * [Leetcode 174] https://leetcode.com/problems/dungeon-game/
     * 
     * <pre>
     * The demons had captured the princess (P) and imprisoned her in the
     * bottom-right corner of a dungeon. The dungeon consists of M x N rooms
     * laid out in a 2D grid. Our valiant knight (K) was initially positioned in
     * the top-left room and must fight his way through the dungeon to rescue
     * the princess. The knight has an initial health point represented by a
     * positive integer. If at any point his health point drops to 0 or below,
     * he dies immediately. Some of the rooms are guarded by demons, so the
     * knight loses health (negative integers) upon entering these rooms; other
     * rooms are either empty (0's) or contain magic orbs that increase the
     * knight's health (positive integers). In order to reach the princess as
     * quickly as possible, the knight decides to move only rightward or
     * downward in each step. Write a function to determine the knight's minimum
     * initial health so that he is able to rescue the princess. For example,
     * given the dungeon below, the initial health of the knight must be at
     * least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
     * </pre>
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon.length == 0)
            return 0;
        int m = dungeon.length, n = dungeon[0].length;
        int[][] minHP = new int[m][n];
        minHP[m - 1][n - 1] = dungeon[m - 1][n - 1] >= 0 ? 1 : -dungeon[m - 1][n - 1] + 1;
        for (int i = m - 2; i >= 0; i--) {
            minHP[i][n - 1] = Math.max(minHP[i + 1][n - 1] - dungeon[i][n - 1], 1);
        }
        for (int j = n - 2; j >= 0; j--) {
            minHP[m - 1][j] = Math.max(minHP[m - 1][j + 1] - dungeon[m - 1][j], 1);
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                minHP[i][j] = Math.max(1, Math.min(minHP[i + 1][j], minHP[i][j + 1]) - dungeon[i][j]);
            }
        }
        return minHP[0][0];
    }
}
