package category.search.binarysearch;

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    private double findKthInArrays(int[] nums1, int startIndex1, int[] nums2, int startIndex2, int k) {
        if (startIndex1 >= nums1.length) {
            return nums2[startIndex2 + k - 1];
        }

        if (startIndex2 >= nums2.length) {
            return nums1[startIndex1 + k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[startIndex1], nums2[startIndex2]);
        }

        int num1 = startIndex1 + k / 2 - 1 < nums1.length ? nums1[startIndex1 + k / 2 - 1] : Integer.MAX_VALUE;
        int num2 = startIndex2 + k / 2 - 1 < nums2.length ? nums2[startIndex2 + k / 2 - 1] : Integer.MAX_VALUE;

        if (num1 > num2) {
            return findKthInArrays(nums1, startIndex1, nums2, startIndex2 + k / 2, k - k / 2);
        } else {
            return findKthInArrays(nums1, startIndex1 + k / 2, nums2, startIndex2, k - k / 2);
        }

    }

    /**
     * [Leetcode 4] https://leetcode.com/problems/median-of-two-sorted-arrays/
     * 
     * <pre>
     * There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted
     * arrays. The overall run time complexity should be O(log (m+n)).
     * </pre>
     * 
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;

        if (len % 2 == 0) {
            return (findKthInArrays(nums1, 0, nums2, 0, len / 2) + findKthInArrays(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;
        } else {
            return findKthInArrays(nums1, 0, nums2, 0, len / 2 + 1);
        }
    }
}
