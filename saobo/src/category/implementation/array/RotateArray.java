package category.implementation.array;

public class RotateArray {

    public static void main(String[] args) {
        int[] num = { 1, 2, 3, 4, 5 };
        new RotateArray().rotate(num, 3);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * [Leetcode 189] https://leetcode.com/problems/rotate-array/
     *
     * <pre>
     * Rotate an array of n elements to the right by k steps.
     * 
     * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
     * 
     * Note:
     * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
     * </pre>
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k == 0 || k == nums.length) {
            return;
        }

        k %= nums.length;
        int lengthOfFirstPart = nums.length - k;

        reverse(nums, 0, lengthOfFirstPart - 1);
        reverse(nums, lengthOfFirstPart, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

}
