package category.dp.cache;

public class ProductOfArray {

    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 4 };
        int[] b = new ProductOfArray().productExceptSelf(a);
        for (int i = 0; i < a.length; i++)
            System.out.println(b[i]);
    }

    /**
     * [Leetcode 238] https://leetcode.com/problems/product-of-array-except-self/ Given an array of n integers where n >
     * 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except
     * nums[i]. Solve it without division and in O(n). For example, given [1,2,3,4], return [24,12,8,6]. Follow up:
     * Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the
     * purpose of space complexity analysis.)
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int[] result = new int[nums.length];

        result[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result[i] = nums[i] * result[i - 1];
        }

        int productFromRight = 1;
        for (int i = nums.length - 1; i > 0; i--) {
            result[i] = result[i - 1] * productFromRight;
            productFromRight *= nums[i];
        }

        result[0] = productFromRight;

        return result;
    }
}
