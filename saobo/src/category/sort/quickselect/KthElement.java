package category.sort.quickselect;

public class KthElement {

    public static void main(String[] args) {
        int[] nums = { 7, 8, 3, 1, 2, 6 };

        System.out.println(new KthElement().findKthLargest(nums, 6));

    }

    private int findKthHelper(int[] nums, int k, int startIndex, int endIndex) {
        int pivot = nums[endIndex];

        int left = startIndex;
        int right = endIndex;

        while (true) {
            while (nums[left] < pivot && left < right) {
                left++;
            }

            while (nums[right] >= pivot && left < right) {
                right--;
            }

            if (left == right) {
                break;
            }

            swap(nums, left, right);
        }

        swap(nums, left, endIndex);

        if (k == left + 1) {
            return pivot;
        } else if (left > k - 1) {
            return findKthHelper(nums, k, startIndex, left - 1);
        } else {
            return findKthHelper(nums, k, left + 1, endIndex);
        }
    }

    /**
     * [Leetcode 215] https://leetcode.com/problems/kth-largest-element-in-an-array/
     *
     * <pre>
     * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
     * not the kth distinct element.
     *
     * For example, Given [3,2,1,5,6,4] and k = 2, return 5.
     *
     * Note: You may assume k is
     * always valid, 1 ≤ k ≤ array's length.
     * </pre>
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return 0;
        }

        return findKthHelper(nums, nums.length - k + 1, 0, nums.length - 1);
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
