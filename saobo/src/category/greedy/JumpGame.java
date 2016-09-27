package category.greedy;

public class JumpGame {

    public static void main(String[] args) {
        int[] a = { 5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0 };

        System.out.println(new JumpGame().jump(a));

    }

    /**
     * [Leetcode 55] https://leetcode.com/problems/jump-game/
     * 
     * <pre>
     * Given an array of non-negative integers, you are initially positioned at
     * the first index of the array. Each element in the array represents your
     * maximum jump length at that position. Determine if you are able to reach
     * the last index.
     * For example:
     * A = [2,3,1,1,4], return true.
     * A = [3,2,1,0,4], return false.
     * </pre>
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int maxReachIndex = 0;
        for (int i = 0; i <= maxReachIndex && i < nums.length; i++) {
            maxReachIndex = Math.max(nums[i] + i, maxReachIndex);
        }

        if (maxReachIndex < nums.length - 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * [Leetcode 45] https://leetcode.com/problems/jump-game-ii/
     *
     * <pre>
     * Given an array of non-negative integers, you are initially positioned at
     * the first index of the array. Each element in the array represents your
     * maximum jump length at that position. Your goal is to reach the last
     * index in the minimum number of jumps. For example: Given array A =
     * [2,3,1,1,4] The minimum number of jumps to reach the last index is 2.
     * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
     * </pre>
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }

        int steps = 0;
        int lastReachIndex = 0;
        int maxReachIndex = 0;
        for (int i = 0; i < nums.length && i <= maxReachIndex; i++) {
            if (i > lastReachIndex) {
                steps++;
                lastReachIndex = maxReachIndex;

                if (lastReachIndex >= nums.length) {
                    return steps;
                }
            }
            maxReachIndex = Math.max(maxReachIndex, nums[i] + i);
        }

        if (maxReachIndex >= nums.length - 1) {
            return steps;
        } else {
            return Integer.MAX_VALUE;
        }
    }

}
