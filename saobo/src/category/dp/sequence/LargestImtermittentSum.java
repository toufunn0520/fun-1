package category.dp.sequence;

import org.junit.Assert;
import org.junit.Test;

public class LargestImtermittentSum {

    /**
     * 一个数组，选出不相邻子序列，要求子序列和最大， [4,9,6]=10. [4,10,3,1,5]=15
     *
     * @param nums
     * @return
     */
    public static int getLargestSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] maxMoney = new int[nums.length + 1];

        maxMoney[0] = 0;
        maxMoney[1] = Math.max(0, nums[0]);

        for (int i = 2; i < nums.length + 1; i++) {
            maxMoney[i] = Math.max(maxMoney[i - 2], Math.max(maxMoney[i - 2] + nums[i - 1], maxMoney[i - 1]));
        }

        return maxMoney[nums.length];
    }

    public static int getLargestSumSpceOptimal(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int prevMax = 0;
        int currentMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int temp = currentMax;
            currentMax = Math.max(prevMax, Math.max(prevMax + nums[i], currentMax));
            prevMax = temp;
        }

        return currentMax;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 10, 4 };
        System.out.println(getLargestSumSpceOptimal(nums));
    }

    @Test
    public void test() {
        int[] nums1 = { 1 };
        int[] nums2 = { -1 };
        int[] nums3 = { 1, 2, 3 };
        int[] nums4 = { 1, 10, 4 };
        int[] nums5 = { 20, -9, 10, 20 };
        int[] nums6 = { -100, -9, -10, 20 };
        Assert.assertEquals(1, getLargestSum(nums1));
        Assert.assertEquals(-1, getLargestSum(nums2));
        Assert.assertEquals(4, getLargestSum(nums3));
        Assert.assertEquals(10, getLargestSum(nums4));
        Assert.assertEquals(40, getLargestSum(nums5));
        Assert.assertEquals(20, getLargestSum(nums6));
    }

    @Test
    public void testOptimal() {
        int[] nums1 = { 1 };
        int[] nums2 = { -1 };
        int[] nums3 = { 1, 2, 3 };
        int[] nums4 = { 1, 10, 4 };
        int[] nums5 = { 20, -9, 10, 20 };
        int[] nums6 = { -100, -9, -10, 20 };
        Assert.assertEquals(1, getLargestSumSpceOptimal(nums1));
        Assert.assertEquals(-1, getLargestSumSpceOptimal(nums2));
        Assert.assertEquals(4, getLargestSumSpceOptimal(nums3));
        Assert.assertEquals(10, getLargestSumSpceOptimal(nums4));
        Assert.assertEquals(40, getLargestSumSpceOptimal(nums5));
        Assert.assertEquals(20, getLargestSumSpceOptimal(nums6));
    }

}
