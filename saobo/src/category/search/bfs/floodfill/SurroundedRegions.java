package category.search.bfs.floodfill;

import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegions {

    private void floodfill(char[][] board, int i, int j, char toBeReplacedChar, char fillingChar) {
        if (board[i][j] != toBeReplacedChar) {
            return;
        }

        int rows = board.length;
        int cols = board[0].length;
        board[i][j] = fillingChar;

        Queue<Integer> processingQueue = new LinkedList<Integer>();
        processingQueue.offer(i * cols + j);

        while (!processingQueue.isEmpty()) {
            int index = processingQueue.poll();

            int y = index / cols;
            int x = index % cols;

            if (y > 0 && board[y - 1][x] == toBeReplacedChar) {
                board[y - 1][x] = fillingChar;
                processingQueue.add((y - 1) * cols + x);
            }

            if (y < rows - 1 && board[y + 1][x] == toBeReplacedChar) {
                board[y + 1][x] = fillingChar;
                processingQueue.add((y + 1) * cols + x);
            }

            if (x > 0 && board[y][x - 1] == toBeReplacedChar) {
                board[y][x - 1] = fillingChar;
                processingQueue.add(y * cols + x - 1);
            }

            if (x < cols - 1 && board[y][x + 1] == toBeReplacedChar) {
                board[y][x + 1] = fillingChar;
                processingQueue.add(y * cols + x + 1);
            }
        }
    }

    /**
     * [Leetcode 130] https://leetcode.com/problems/surrounded-regions/
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
     * @param board
     */
    public void surroundedRegions(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            floodfill(board, i, 0, 'O', '2');
            floodfill(board, i, cols - 1, 'O', '2');
        }

        for (int i = 0; i < cols; i++) {
            floodfill(board, 0, i, 'O', '2');
            floodfill(board, rows - 1, i, 'O', '2');

        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '2') {
                    board[i][j] = 'O';
                }
            }
        }
    }
}
