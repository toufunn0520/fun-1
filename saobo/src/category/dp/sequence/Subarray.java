package category.dp.sequence;

public class Subarray {

    public static void main(String[] args) {
        int[] nums = { 2, 3, 1, 2, 4, 3 };

    }

    private int getMax(int num1, int num2, int num3) {
        return Math.max(num1, Math.max(num2, num3));
    }

    private int getMin(int num1, int num2, int num3) {
        return Math.min(num1, Math.min(num2, num3));
    }

    /**
     * [Leetcode 152] https://leetcode.com/problems/maximum-product-subarray/
     *
     * <pre>
     * Find the contiguous subarray within an array (containing at least one number) which has the largest product. For
     * example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
     * </pre>
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

    /**
     * [Leetcode 53] https://leetcode.com/problems/maximum-subarray/ Find the contiguous subarray within an array
     * (containing at least one number) which has the largest sum. For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
     * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sum > maxSum) {
                maxSum = sum;
            }

            if (sum < 0) {
                sum = 0;
            }
        }

        return maxSum;
    }

}
