package category.dp.sequence;

import org.junit.Assert;
import org.junit.Test;

public class LongestSubsetPalindrome {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 2, 1 };
        System.out.println(new LongestSubsetPalindrome().calculate(nums));
    }

    /**
     * [Linkedin onsite] Given an array of integers. Try to get the longest subset palindrome length without changing
     * the order from original input.
     *
     * @param nums
     * @return
     */
    public int calculate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // palindromeLength[i][j]: the longest length of subset palindrome of subarray from index i to index j
        int[][] palindromeLength = new int[nums.length][nums.length];

        for (int i = 0; i < nums.length; i++) {
            palindromeLength[i][i] = 1;
            if (i < nums.length - 1) {
                if (nums[i] == nums[i + 1]) {
                    palindromeLength[i][i + 1] = 2;
                } else {
                    palindromeLength[i][i + 1] = 1;
                }
            }
        }

        for (int i = 2; i < nums.length; i++) {
            for (int j = 0; j + i < nums.length; j++) {
                palindromeLength[j][j + i] = Math.max(palindromeLength[j][j + i - 1], palindromeLength[j + 1][j + i]);
                if (nums[j] == nums[j + i]) {
                    palindromeLength[j][j + i] = Math.max(palindromeLength[j][j + i],
                            palindromeLength[j + 1][i + j - 1] + 2);
                }
            }
        }

        return palindromeLength[0][nums.length - 1];
    }

    @Test
    public void testWithAllPartOfPalindrome() {
        int[] nums1 = { 1, 2, 3, 3, 2, 1 };
        int[] nums2 = { 1, 2, 3, 2, 1 };
        Assert.assertEquals(6, new LongestSubsetPalindrome().calculate(nums1));
        Assert.assertEquals(5, new LongestSubsetPalindrome().calculate(nums2));
    }

    @Test
    public void testWithAllSameNumbers() {
        int[] nums = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        Assert.assertEquals(10, new LongestSubsetPalindrome().calculate(nums));
    }

    @Test
    public void testWithAllUniqueNumbers() {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8 };
        Assert.assertEquals(1, new LongestSubsetPalindrome().calculate(nums));
    }

    @Test
    public void testWithHeadEqualsToTail() {
        int[] nums1 = { 1, 2, 3, 4, 5, 6, 7, 1 }; // 1,2,1
        int[] nums2 = { 1, 1, 2, 3, 5, 2, 1, 1 }; // 1,1,2,3,2,1,1
        int[] nums3 = { 1, 4, 1, 2, 3, 5, 2, 1, 1 }; // 1,1,2,3,2,1,1
        Assert.assertEquals(3, new LongestSubsetPalindrome().calculate(nums1));
        Assert.assertEquals(7, new LongestSubsetPalindrome().calculate(nums2));
        Assert.assertEquals(7, new LongestSubsetPalindrome().calculate(nums3));
    }

    @Test
    public void testWithHeadNotEqualsToTail() {
        int[] nums1 = { 1, 2, 3, 3, 3, 6, 7, 8 }; // 3,3,3
        int[] nums2 = { 1, 1, 1, 4, 2, 4, 1, 1, 8 }; // 1,1,4,2,4,1,1
        Assert.assertEquals(3, new LongestSubsetPalindrome().calculate(nums1));
        Assert.assertEquals(7, new LongestSubsetPalindrome().calculate(nums2));
    }
}
