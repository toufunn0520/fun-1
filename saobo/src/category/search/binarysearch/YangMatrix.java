package category.search.binarysearch;

public class YangMatrix {

    /**
     * <pre>
     * [Leetcode 74] https://leetcode.com/problems/search-a-2d-matrix/
     * [Leetcode 240] https://leetcode.com/problems/search-a-2d-matrix-ii/
     *
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     *
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     * For example,
     *
     * Consider the following matrix:
     *
     * [
     *   [1,   3,  5,  7],
     *   [10, 11, 16, 20],
     *   [23, 30, 34, 50]
     * ]
     * Given target = 3, return true.
     *
     * </pre>
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int startX = cols - 1, startY = 0;

        while (startX >= 0 && startY < rows) {
            if (matrix[startY][startX] == target) {
                return true;
            } else if (matrix[startY][startX] < target) {
                startY++;
            } else {
                startX--;
            }
        }

        return false;
    }
}
