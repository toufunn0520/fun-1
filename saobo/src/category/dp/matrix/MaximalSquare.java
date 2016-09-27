package category.dp.matrix;

public class MaximalSquare {

    public static void main(String[] args) {
        char[][] matrix = { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
                { '1', '0', '0', '1', '0' } };
        System.out.println(maximalSquare(matrix));

    }

    /**
     * [Leetcode 221] https://leetcode.com/problems/maximal-square/
     *
     * <pre>
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
     * For example, given the following matrix:
     *
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     * Return 4.
     * </pre>
     *
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        // numOfSquare[i][j] means ending with i-1 j-1, the max number of squares
        int[][] numOfSquare = new int[row + 1][col + 1];
        for (int i = 0; i < row; i++) {
            numOfSquare[i][0] = 0;
        }

        for (int i = 0; i < col; i++) {
            numOfSquare[0][i] = 0;
        }

        int maxNumOfSquares = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    numOfSquare[i][j] = Math.min(Math.min(numOfSquare[i - 1][j - 1], numOfSquare[i][j - 1]),
                            numOfSquare[i - 1][j]) + 1;
                    maxNumOfSquares = Math.max(maxNumOfSquares, numOfSquare[i][j]);
                } else {
                    numOfSquare[i][j] = 0;
                }
            }
        }

        return maxNumOfSquares * maxNumOfSquares;
    }
}
