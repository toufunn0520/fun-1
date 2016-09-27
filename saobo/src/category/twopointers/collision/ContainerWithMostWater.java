package category.twopointers.collision;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * [Leetcode 11] https://leetcode.com/problems/container-with-most-water/
     * 
     * <pre>
     * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical
     * lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
     * with x-axis forms a container, such that the container contains the most water. Note: You may not slant the
     * container.
     * </pre>
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int maxArea = 0;
        int start = 0;
        int end = height.length - 1;
        while (start < end) {
            int currentHeight = Math.min(height[start], height[end]);
            maxArea = Math.max(currentHeight * (end - start), maxArea);

            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }

        return maxArea;
    }

}
