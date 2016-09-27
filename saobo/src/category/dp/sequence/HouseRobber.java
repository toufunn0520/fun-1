package category.dp.sequence;

import java.util.Arrays;

public class HouseRobber {

    public static void main(String[] args) {
        int[] nums = { 100, 4, 10, 2, 29, 11, 23, 40 };
        System.out.println(new HouseRobber().rob2(nums));

    }

    /**
     * [Leetcode 198] https://leetcode.com/problems/house-robber/
     *
     * <pre>
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
     * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system
     * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
     * Given a list of non-negative integers representing the amount of money of each house, determine the maximum
     * amount of money you can rob tonight without alerting the police.
     * </pre>
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] maxMoney = new int[nums.length + 1];

        maxMoney[0] = 0;
        maxMoney[1] = nums[0];

        for (int i = 2; i < nums.length + 1; i++) {
            maxMoney[i] = Math.max(maxMoney[i - 2] + nums[i - 1], maxMoney[i - 1]);
        }

        return maxMoney[nums.length];
    }

    /**
     * [Leetcode 213] https://leetcode.com/problems/house-robber-ii/
     * 
     * <pre>
     * After robbing those houses on that street, the thief has found himself a new place for his thievery so that he
     * will not get too much attention. This time, all houses at this place are arranged in a circle. That means the
     * first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as
     * for those in the previous street. Given a list of non-negative integers representing the amount of money of each
     * house, determine the maximum amount of money you can rob tonight without alerting the police.
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int robFirstHouse = rob(Arrays.copyOfRange(nums, 0, nums.length - 1));
        int notRobFirstHouse = rob(Arrays.copyOfRange(nums, 1, nums.length));

        return Math.max(robFirstHouse, notRobFirstHouse);
    }

}
