package category.circularswap;

public class MissingNumber {

    public static void main(String[] args) {
        int[] nums = { 1, 0, 2 };

        System.out.println(new MissingNumber().missingNumber(nums));

    }

    /**
     * [Leetcode 41] https://leetcode.com/problems/first-missing-positive/
     * 
     * <pre>
     * Given an unsorted integer array, find the first missing positive integer. For example, Given [1,2,0] return 3,
     * and [3,4,-1,1] return 2. Your algorithm should run in O(n) time and uses constant space. [Trick] When nums[i] ==
     * nums[nums[i] - 1] needs to pass
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null) {
            return 1;
        }

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i] != i + 1 && nums[i] > 0 && nums[i] <= len) {
                int temp = nums[i];
                if (nums[i] == nums[temp - 1]) {
                    break;
                }

                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return len + 1;
    }

    /**
     * <pre>
     * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
     * 
     * For example,
     * Given nums = [0, 1, 3] return 2.
     * 
     * Note:
     * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
     * </pre>
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                int temp = nums[i];
                if (temp > nums.length - 1) {
                    break;
                }

                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        return nums.length;
    }
}
