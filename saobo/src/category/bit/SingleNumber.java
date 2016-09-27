package category.bit;

public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 2, 1, 5 };

        int[] ret = new SingleNumber().singleNumber3(nums);

        for (int i : ret) {
            System.out.println(i);
        }

    }

    /**
     * [Leetcode 136] https://leetcode.com/problems/single-number/
     *
     * <pre>
     * Given an array of integers, every element appears
     * twice except for one. Find that single one.
     * </pre>
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int singleNumber = 0;
        for (int num : nums) {
            singleNumber = num ^ singleNumber;
        }

        return singleNumber;
    }

    /**
     * [Leetcode 137] https://leetcode.com/problems/single-number-ii/
     *
     * <pre>
     * Given an array of integers, every element appears
     * three times except for one. Find that single one.
     * </pre>
     *
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        if (nums == null) {
            return 0;
        }

        // ones means the bits only show up once
        // twos mean the bits only show up twice
        // threes mean the bits only show up three times
        int ones = 0, twos = 0, threes = 0;
        for (int i = 0; i < nums.length; i++) {
            twos |= ones & nums[i];
            ones ^= nums[i];
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }

    /**
     * [Leetcode 260] https://leetcode.com/problems/single-number-iii/
     *
     * <pre>
     * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear 
     * exactly twice. Find the two elements that appear only once.
     * 
     * For example:
     * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
     * 
     * Note:
     * The order of the result is not important. So in the above example, [5, 3] is also correct.
     * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
     * </pre>
     *
     * @param nums
     * @return
     */
    public int[] singleNumber3(int[] nums) {
        if (nums == null || nums.length < 2) {
            return null;
        }

        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        int count = 0;
        while ((xor & 1) == 0) {
            count++;
            xor >>= 1;
        }

        int xor1 = 0, xor2 = 0;
        for (int num : nums) {
            if ((num >> count & 1) == 0) {
                xor1 ^= num;
            } else {
                xor2 ^= num;
            }
        }

        int[] singleNumbers = { xor1, xor2 };
        return singleNumbers;
    }

}
