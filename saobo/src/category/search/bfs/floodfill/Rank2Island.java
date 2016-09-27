package category.search.bfs.floodfill;

import interview.utils.MatrixUtils;

import java.util.LinkedList;
import java.util.Queue;

public class Rank2Island {

    public static void main(String[] args) {
        String[] input = { "001110", "100001", "101101", "100001", "011110" };
        int[][] board = Rank2IslandTest.generateBoard(input);

        System.out.println("The board is ");
        MatrixUtils.printMatrix(board);

        System.out.println("There are " + new Rank2Island().getRank2Island(board) + " rank2 islands");
    }

    private void floodFill(int[][] board, int i, int j, int valueToBeChanged, int newValue) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != valueToBeChanged) {
            return;
        }

        Queue<Integer> processingQueue = new LinkedList<Integer>();
        board[i][j] = newValue;
        int rows = board.length;
        int cols = board[0].length;
        processingQueue.add(i * cols + j);

        while (!processingQueue.isEmpty()) {
            int index = processingQueue.poll();
            int rowIndex = index / cols;
            int colIndex = index % cols;

            if (rowIndex > 0 && board[rowIndex - 1][colIndex] == valueToBeChanged) {
                board[rowIndex - 1][colIndex] = newValue;
                processingQueue.offer((rowIndex - 1) * cols + colIndex);
            }

            if (rowIndex < rows - 1 && board[rowIndex + 1][colIndex] == valueToBeChanged) {
                board[rowIndex + 1][colIndex] = newValue;
                processingQueue.offer((rowIndex + 1) * cols + colIndex);
            }

            if (colIndex > 0 && board[rowIndex][colIndex - 1] == valueToBeChanged) {
                board[rowIndex][colIndex - 1] = newValue;
                processingQueue.offer(rowIndex * cols + colIndex - 1);
            }

            if (colIndex < cols - 1 && board[rowIndex][colIndex + 1] == valueToBeChanged) {
                board[rowIndex][colIndex + 1] = newValue;
                processingQueue.offer(rowIndex * cols + colIndex + 1);
            }
        }

    }

    /**
     * [Google phone]
     * 
     * <pre>
     * count rank 2 islands, where a rank 2 island is an island inside
     * a lake located on a continent. A continent is a piece of land located in
     * the ocean; the ocean is any body of water that touches the edges of the map.
     * 
     * Example:
     * 000000000
     * 000001100
     * 001111100
     * 011000100
     * 001010100
     * 001000100
     * 001111100
     * 000000000
     * 
     * returns 1.
     * </pre>
     *
     * @param board
     * @return
     */
    public int getRank2Island(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }

        int rows = board.length;
        int cols = board[0].length;

        // 2 is ocean
        for (int i = 0; i < rows; i++) {
            floodFill(board, i, 0, 0, 2);
            floodFill(board, i, cols - 1, 0, 2);
        }

        for (int i = 0; i < cols; i++) {
            floodFill(board, 0, i, 0, 2);
            floodFill(board, rows - 1, i, 0, 2);
        }

        // 3 is continent
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 2) {
                    floodFill(board, i + 1, j, 1, 3);
                    floodFill(board, i, j + 1, 1, 3);
                    floodFill(board, i - 1, j, 1, 3);
                    floodFill(board, i, j - 1, 1, 3);
                }
            }
        }

        int rank2Count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 1) {
                    rank2Count++;
                }
            }
        }

        return rank2Count;
    }

}
