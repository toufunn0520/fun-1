package category.search.bfs.floodfill;

import org.junit.Assert;
import org.junit.Test;

public class Rank2IslandTest {

    public static int[][] generateBoard(String[] strings) {
        if (strings == null || strings.length == 0 || strings[0].length() == 0) {
            return null;
        }

        int[][] board = new int[strings.length][strings[0].length()];

        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[0].length(); j++) {
                board[i][j] = Integer.parseInt(strings[i].charAt(j) + "");
            }
        }

        return board;
    }

    @Test
    public void testWithContinuousContinent() {
        /**
         * <pre>
         * 000000000
         * 000001100
         * 001111100
         * 011000100
         * 001010100
         * 001000100
         * 001111100
         * 000000000
         * </pre>
         */
        String[] input = { "000000000", "000001100", "001111100", "011000100", "001010100", "001000100", "001111100",
                "000000000" };
        int[][] board = generateBoard(input);
        Assert.assertEquals(1, new Rank2Island().getRank2Island(board));
    }

    @Test
    public void testWithNonClosedContinuousContinent() {
        /**
         * <pre>
         * 001110
         * 100001
         * 101101
         * 100001
         * 011110
         * </pre>
         */
        String[] input = { "001110", "100001", "101101", "100001", "011110" };
        int[][] board = generateBoard(input);
        Assert.assertEquals(0, new Rank2Island().getRank2Island(board));
    }

    @Test
    public void testWithNonContinuousContinent() {
        /**
         * <pre>
         * 011110
         * 100001
         * 101101
         * 100001
         * 011110
         * </pre>
         */
        String[] input = { "011110", "100001", "101101", "100001", "011110" };
        int[][] board = generateBoard(input);
        Assert.assertEquals(2, new Rank2Island().getRank2Island(board));
    }
}
