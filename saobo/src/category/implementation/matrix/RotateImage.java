package category.implementation.matrix;

public class RotateImage {

    /**
     * [Leetcode 48] https://leetcode.com/problems/rotate-image/
     * 
     * <pre>
     * You are given an n x n 2D matrix representing an image. Rotate the image by 90 degrees (clockwise). Follow up:
     * Could you do this in-place?
     * </pre>
     * 
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length != matrix.length) {
            return;
        }

        int len = matrix.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i; j++) {
                swap(matrix, i, j, len - j - 1, len - i - 1);
            }
        }

        for (int i = 0; i < len / 2; i++) {
            for (int j = 0; j < len; j++) {
                swap(matrix, i, j, len - 1 - i, j);
            }
        }

    }

    private void swap(int[][] matrix, int row1, int col1, int row2, int col2) {
        int temp = matrix[row1][col1];
        matrix[row1][col1] = matrix[row2][col2];
        matrix[row2][col2] = temp;
    }
}
