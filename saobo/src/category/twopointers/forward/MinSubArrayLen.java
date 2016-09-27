package category.twopointers.forward;

public class MinSubArrayLen {

    /**
     * [Leetcode 209] https://leetcode.com/problems/minimum-size-subarray-sum/
     * 
     * <pre>
     * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which
     * the sum ≥ s. If there isn't one, return 0 instead. For example, given the array [2,3,1,2,4,3] and s = 7, the
     * subarray [4,3] has the minimal length under the problem constraint.
     * </pre>
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int startIndex = 0;
        int endIndex = 0;
        int currentSum = nums[0];

        while (currentSum < s && endIndex < nums.length - 1) {
            currentSum += nums[++endIndex];
        }

        if (currentSum < s) {
            return 0;
        }

        int minLength = endIndex - startIndex + 1;

        while (endIndex < nums.length) {
            currentSum -= nums[startIndex];
            startIndex++;

            while (currentSum < s && endIndex < nums.length - 1) {
                currentSum += nums[++endIndex];
            }

            if (currentSum < s) {
                break;
            } else {
                minLength = Math.min(minLength, endIndex - startIndex + 1);
            }
        }

        return minLength;
    }
}
