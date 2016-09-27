package category.implementation.matrix;

import interview.utils.ListUtils;
import interview.utils.MatrixUtils;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };

        List<Integer> result = new SpiralMatrix().spiralOrder(matrix);
        ListUtils.listPrint(result);
        System.out.println("~~~~~~~~~~~");

        int[][] resultMatrix = new SpiralMatrix().generateMatrix(0);

        MatrixUtils.printMatrix(resultMatrix);

    }

    /**
     * [Leetcode ] https://leetcode.com/problems/spiral-matrix-ii/
     *
     * <pre>
     * Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.
     * 
     * For example,
     * Given n = 3,
     * 
     * You should return the following matrix:
     * [
     *  [ 1, 2, 3 ],
     *  [ 8, 9, 4 ],
     *  [ 7, 6, 5 ]
     * ]
     * </pre>
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        if (n < 0) {
            return new int[0][0];
        }

        int[][] matrix = new int[n][n];

        int startCol = 0;
        int endCol = n - 1;
        int startRow = 0;
        int endRow = n - 1;
        int j = 1;
        while (startRow <= endRow && startCol <= endCol) {
            for (int i = startCol; i <= endCol; i++) {
                matrix[startRow][i] = j++;
            }

            for (int i = startRow + 1; i <= endRow - 1; i++) {
                matrix[i][endCol] = j++;
            }

            if (startRow != endRow) {
                for (int i = endCol; i >= startCol; i--) {
                    matrix[endRow][i] = j++;
                }
            }

            if (startCol != endCol) {
                for (int i = endRow - 1; i >= startRow + 1; i--) {
                    matrix[i][startCol] = j++;
                }
            }

            startCol++;
            startRow++;
            endCol--;
            endRow--;
        }

        return matrix;
    }

    /**
     * [Leetcode 54] https://leetcode.com/problems/spiral-matrix/
     * 
     * <pre>
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
     *
     * For example,
     * Given the following matrix:
     *
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     * You should return [1,2,3,6,9,8,7,4,5].
     * </pre>
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int startCol = 0;
        int endCol = cols - 1;
        int startRow = 0;
        int endRow = rows - 1;
        while (startRow <= endRow && startCol <= endCol) {

            for (int i = startCol; i <= endCol; i++) {
                result.add(matrix[startRow][i]);
            }

            for (int i = startRow + 1; i <= endRow - 1; i++) {
                result.add(matrix[i][endCol]);
            }

            if (startRow != endRow) {
                for (int i = endCol; i >= startCol; i--) {
                    result.add(matrix[endRow][i]);
                }
            }

            if (startCol != endCol) {
                for (int i = endRow - 1; i >= startRow + 1; i--) {
                    result.add(matrix[i][startCol]);
                }
            }

            startCol++;
            startRow++;
            endCol--;
            endRow--;
        }

        return result;
    }

}
