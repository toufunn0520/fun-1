package category.search.binarysearch;

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = { 1, 3, 5 };

        System.out.println(new SearchInRotatedSortedArray().search2(nums, 5));
    }

    /**
     * [Leetcode 33] https://leetcode.com/problems/search-in-rotated-sorted-array/
     *
     * <pre>
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand. (i.e., 0 1 2 4 5 6 7 might become 4 5
     * 6 7 0 1 2). You are given a target value to search. If found in the array return its index, otherwise return -1.
     * You may assume no duplicate exists in the array.
     * </pre>
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[start] < nums[mid]) {
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }

        if (nums[start] == target) {
            return start;
        } else if (nums[end] == target) {
            return end;
        } else {
            return -1;
        }
    }

    /**
     * [Leetcode 81] https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
     * 
     * <pre>
     * What if duplicates are allowed? Would this affect the run-time complexity? How and why? Write a function to
     * determine if a given target is in the array.
     * </pre>
     * 
     * @param nums
     * @param target
     * @return
     */
    public boolean search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[start] < nums[mid]) {
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[mid] < nums[start]) {
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                start++;
            }

        }

        if (nums[start] == target || nums[end] == target) {
            return true;
        } else {
            return false;
        }
    }
}
