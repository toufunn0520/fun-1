package category.implementation.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class MatrixIterate {

    /**
     * [Snap*chat] print matrix diagonally
     *
     * @param matrix
     */
    public List<List<Integer>> diagonalIterateMatrix(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return result;
        }

        int rows = matrix.length, cols = matrix[0].length;
        int i = 0, j = 0;
        while (i < rows && j < cols) {
            int currentJ = j, currentI = i;
            List<Integer> line = new ArrayList<Integer>();

            while (currentI < rows && currentJ >= 0) {
                line.add(matrix[currentI++][currentJ--]);
            }
            result.add(line);
            if (j < cols - 1) {
                j++;
                i = 0;
            } else {
                i++;
                j = cols - 1;
            }
        }

        return result;
    }

    @Test
    public void testWithDifferentInputs() {
        MatrixIterate iterator = new MatrixIterate();

        int[][] oneNumber = { { 1 } };
        int[][] oneLine = { { 1, 2, 3 } };
        int[][] multipleLines = { { 1, 2, 3 }, { 4, 5, 6 } };
        int[][] square = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

        Assert.assertEquals(Arrays.asList(Arrays.asList(1)), iterator.diagonalIterateMatrix(oneNumber));
        Assert.assertEquals(Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3)),
                iterator.diagonalIterateMatrix(oneLine));
        Assert.assertEquals(
                Arrays.asList(Arrays.asList(1), Arrays.asList(2, 4), Arrays.asList(3, 5), Arrays.asList(6)),
                iterator.diagonalIterateMatrix(multipleLines));
        Assert.assertEquals(Arrays.asList(Arrays.asList(1), Arrays.asList(2, 4), Arrays.asList(3, 5, 7),
                Arrays.asList(6, 8), Arrays.asList(9)), iterator.diagonalIterateMatrix(square));
    }
}
