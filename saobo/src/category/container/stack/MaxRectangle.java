package category.container.stack;

import java.util.Stack;

public class MaxRectangle {

    public static void main(String[] args) {
        char[][] matrix = { { '1', '1', '1' }, { '1', '1', '1' } };

        System.out.println(maximalRectangle(matrix));
    }

    /**
     * [Leetcode 85] https://leetcode.com/problems/maximal-rectangle/
     * 
     * <pre>
     * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its
     * area.
     * </pre>
     *
     * @param matrix
     * @return
     */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int[] height = new int[matrix[0].length + 1];
        Stack<Integer> indices;
        int maxArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            indices = new Stack<Integer>();
            for (int j = 0; j < height.length; j++) {
                if (j < height.length - 1) {
                    if (matrix[i][j] == '1') {
                        height[j]++;
                    } else {
                        height[j] = 0;
                    }
                }

                while (!indices.isEmpty() && height[j] < height[indices.peek()]) {
                    int index = indices.pop();
                    int width = indices.isEmpty() ? j : j - indices.peek() - 1;
                    maxArea = Math.max(maxArea, width * height[index]);
                }
                indices.push(j);
            }
        }

        return maxArea;
    }
}
