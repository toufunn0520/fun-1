package category.dp.sequence;

public class MaximumProductSubarray {

    /**
     * Find the contiguous subarray within an array (containing at least one number) which has the largest product. For
     * example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
     * 
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            max[i] = getMax(nums[i], max[i - 1] * nums[i], min[i - 1] * nums[i]);
            min[i] = getMin(nums[i], min[i - 1] * nums[i], max[i - 1] * nums[i]);
        }

        int maxProduct = max[0];
        for (int i = 1; i < max.length; i++) {
            maxProduct = Math.max(maxProduct, max[i]);
        }
        return maxProduct;
    }

    private int getMax(int num1, int num2, int num3) {
        return Math.max(num1, Math.max(num2, num3));
    }

    private int getMin(int num1, int num2, int num3) {
        return Math.min(num1, Math.min(num2, num3));
    }
}
