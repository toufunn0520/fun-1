package category.search.binarysearch;

public class Searcher {

    public static void main(String[] args) {
        int[] A = { 3, 5, 1 };

        System.out.println(new Searcher().search(A, 1));
    }

    /**
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand. (i.e., 0 1 2 4 5 6 7 might become 4 5
     * 6 7 0 1 2). You are given a target value to search. If found in the array return its index, otherwise return -1.
     * You may assume no duplicate exists in the array.
     *
     * @param A
     * @param target
     * @return
     */
    public int search(int[] A, int target) {

        int start = 0;
        int end = A.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            }
            if (A[start] < A[mid]) {
                // situation 1, red line
                if (A[start] <= target && target <= A[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                // situation 2, green line
                if (A[mid] <= target && target <= A[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        } // while

        if (A[start] == target) {
            return start;
        }
        if (A[end] == target) {
            return end;
        }
        return -1;
    }

    /**
     * [Leetcode 35] https://leetcode.com/problems/search-insert-position/
     *
     * <pre>
     * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     *
     * You may assume no duplicates in the array.
     *
     * Here are few examples.
     * [1,3,5,6], 5 → 2
     * [1,3,5,6], 2 → 1
     * [1,3,5,6], 7 → 4
     * [1,3,5,6], 0 → 0
     * </pre>
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0 || nums[0] >= target) {
            return 0;
        }

        int start = 0;
        int end = nums.length - 1;

        if (nums[end] < target) {
            return nums.length;
        }

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                end = mid;
            } else {
                start = mid;
            }

        }

        return end;
    }

    /**
     * [Leetcode 34] https://leetcode.com/problems/search-for-a-range/
     *
     * <pre>
     * Given a sorted array of integers, find the starting and ending position
     * of a given target value. Your algorithm's runtime complexity must be in
     * the order of O(log n). If the target is not found in the array, return
     * [-1, -1]. For example, Given [5, 7, 7, 8, 8, 10] and target value 8,
     * return [3, 4].
     * </pre>
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = { -1, -1 };
        if (nums == null) {
            return result;
        }

        int left = 0;
        int right = nums.length - 1;

        // search for left bound.
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[start] == target) {
            left = start;
        } else {
            left = start + 1;
        }

        // search for right bound.
        end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (nums[end] == target) {
            right = end;
        } else {
            right = end - 1;
        }

        if (left > right) {
            return result;
        } else {
            result[0] = left;
            result[1] = right;
            return result;
        }
    }

}
