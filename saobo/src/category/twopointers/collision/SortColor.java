package category.twopointers.collision;

public class SortColor {

    /**
     * [Leetcode 75] https://leetcode.com/problems/sort-colors/
     * 
     * <pre>
     * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are
     * adjacent, with the colors in the order red, white and blue. Here, we will use the integers 0, 1, and 2 to
     * represent the color red, white, and blue respectively. Note: You are not suppose to use the library's sort
     * function for this problem.
     * </pre>
     * 
     * @param nums
     */
    public void sortColors(int[] nums) {
        if (nums == null) {
            return;
        }

        int red = 0, i = 0;
        int blue = nums.length - 1;
        while (i < blue + 1) {
            if (nums[i] == 0) {
                swap(nums, i, red);
                red++;
            }
            if (nums[i] == 2) {
                swap(nums, i, blue);
                blue--;
                continue;
            }
            i++;
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
