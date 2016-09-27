package category.twopointers.collision;

public class TrappingRainWater {

    public static void main(String[] args) {
        int[] nums = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };

        System.out.println(new TrappingRainWater().trap(nums));

    }

    /**
     * [Leetcode 42] https://leetcode.com/problems/trapping-rain-water/
     *
     * <pre>
     * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much
     * water it is able to trap after raining.
     * 
     * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
     * </pre>
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;

        int total = 0;
        int blocks = 0;
        int prev = 0;
        while (left <= right) {
            int currentMin = Math.min(height[left], height[right]);
            if (currentMin > prev) {
                total += (currentMin - prev) * (right - left + 1);
                prev = currentMin;
            }

            if (height[left] < height[right]) {
                blocks += height[left];
                left++;
            } else {
                blocks += height[right];
                right--;
            }
        }

        return total - blocks;
    }

}
