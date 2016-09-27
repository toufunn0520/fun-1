package category.implementation.matrix;

public class SetZeros {

    /**
     * [Leetcode 73] https://leetcode.com/problems/set-matrix-zeroes/
     * 
     * <pre>
     * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
     * </pre>
     * 
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean firstRowContainsZero = false;
        boolean firstColContainsZero = false;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                firstColContainsZero = true;
                break;
            }
        }

        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == 0) {
                firstRowContainsZero = true;
                break;
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < rows; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < cols; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < cols; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < rows; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        if (firstRowContainsZero) {
            for (int i = 0; i < cols; i++) {
                matrix[0][i] = 0;
            }
        }

        if (firstColContainsZero) {
            for (int j = 0; j < rows; j++) {
                matrix[j][0] = 0;
            }
        }
    }

}
