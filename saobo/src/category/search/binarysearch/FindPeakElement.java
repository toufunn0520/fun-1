package category.search.binarysearch;

public class FindPeakElement {

    /**
     * [Leetcode 162] https://leetcode.com/problems/find-peak-element/
     * 
     * <pre>
     * A peak element is an element that is greater than its neighbors. Given an input array where num[i] ≠ num[i+1],
     * find a peak element and return its index. The array may contain multiple peaks, in that case return the index to
     * any one of the peaks is fine. You may imagine that num[-1] = num[n] = -∞. For example, in array [1, 2, 3, 1], 3
     * is a peak element and your function should return the index number 2.
     * </pre>
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length <= 1 || nums[0] > nums[1]) {
            return 0;
        }

        int len = nums.length;

        if (nums[len - 1] > nums[len - 2]) {
            return len - 1;
        }

        int start = 1;
        int end = len - 2;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                return mid;
            } else if (nums[mid] < nums[mid + 1]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }
}
