package category.twopointers.forward;

public class MoveZeros {

    public static void main(String[] args) {
        int[] nums = { 0, 1, 0, 3, 12 };
        moveZeros(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    /**
     * [Leetcode 283] https://leetcode.com/problems/move-zeroes/
     * 
     * <pre>
     * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the
     * non-zero elements.
     *
     * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
     *
     * Note:
     * You must do this in-place without making a copy of the array.
     * Minimize the total number of operations.
     * </pre>
     *
     * @param nums
     */
    public static void moveZeros(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int indexOfNonZeros = 0;
        int indexOfZeros = 0;

        while (indexOfNonZeros < nums.length && indexOfZeros < nums.length) {

            while (indexOfZeros < nums.length && nums[indexOfZeros] != 0) {
                indexOfZeros++;
            }

            while ((indexOfNonZeros < nums.length && nums[indexOfNonZeros] == 0) || indexOfNonZeros < indexOfZeros) {
                indexOfNonZeros++;
            }

            if (indexOfNonZeros < nums.length && indexOfZeros < nums.length) {
                swap(nums, indexOfZeros, indexOfNonZeros);
            }
        }
    }

    private static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
