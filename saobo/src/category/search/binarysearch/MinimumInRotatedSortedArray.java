package category.search.binarysearch;

public class MinimumInRotatedSortedArray {

    /**
     * Suppose a sorted array is rotated at some pivot unknown to you
     * beforehand. (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). Find the
     * minimum element. You may assume no duplicate exists in the array.
     * 
     * @param num
     * @return
     */
    public int findMin(int[] num) {
        int start = 0, end = num.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (num[mid] >= num[end]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (num[start] < num[end]) {
            return num[start];
        } else {
            return num[end];
        }
    }

    /**
     * Follow up for "Find Minimum in Rotated Sorted Array": What if duplicates
     * are allowed? Would this affect the run-time complexity? How and why?
     *
     * @param nums
     * @return
     */
    public int findMin2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            if (nums[start] < nums[end]) {
                return nums[start];
            }

            int mid = start + (end - start) / 2;
            if (nums[start] < nums[mid]) {
                start = mid;
            } else if (nums[start] > nums[mid]) {
                end = mid;
            } else {
                start++;
            }
        }

        return nums[start] < nums[end] ? nums[start] : nums[end];
    }
}
