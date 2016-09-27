package category.container.stack;

import java.util.Arrays;
import java.util.Stack;

public class LargestRectangle {

    public static void main(String[] args) {
        int[] height = { 2, 1, 5, 6, 2, 3 };

        System.out.println(new LargestRectangle().largestRectangleArea(height));
    }

    /**
     * [Leetcode 84] https://leetcode.com/problems/largest-rectangle-in-histogram/
     *
     * <pre>
     * Given n non-negative integers representing the histogram's bar height
     * where the width of each bar is 1, find the area of largest rectangle in
     * the histogram. For example, Given height = [2,1,5,6,2,3], return 10.
     * </pre>
     *
     * @param height
     * @return
     */
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        height = Arrays.copyOf(height, height.length + 1); // add a zero
        int maxArea = 0;
        Stack<Integer> indices = new Stack<Integer>();
        for (int i = 0; i < height.length; i++) {
            // update only when current height is smaller.
            while (!indices.isEmpty() && height[i] < height[indices.peek()]) {
                int index = indices.pop();
                int width = (indices.isEmpty() ? i : i - indices.peek() - 1);
                maxArea = Math.max(maxArea, height[index] * width);
            }
            indices.push(i); // push index into stack
        }
        return maxArea;
    }
}
