package category.implementation.array;

public class RemoveDuplicatesfromSortedArray {

    public static void main(String[] args) {
        int[] a = { 1, 1, 1, 2, 2, 3 };

        int len = new RemoveDuplicatesfromSortedArray().removeDuplicates(a);

        for (int i = 0; i < len; i++)
            System.out.println(a[i]);
        // System.out.println(len);
    }

    /**
     * [Leetcode 26] https://leetcode.com/problems/remove-duplicates-from-sorted-array/
     *
     * <pre>
     * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
     * Do not allocate extra space for another array, you must do this in place with constant memory.
     *
     * For example,
     * Given input array nums = [1,1,2], Your function should return length = 2, with the first two elements of
     * nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
     * </pre>
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return 1;
        }

        int cur = nums[0];
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != cur) {
                cur = nums[i];
                nums[index] = nums[i];
                index++;
            }
        }

        return index;
    }

    /**
     * [Leetcode 80] https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/submissions/
     * 
     * <pre>
     * Follow up for "Remove Duplicates":
     * What if duplicates are allowed at most twice?
     * 
     * For example,
     * Given sorted array nums = [1,1,1,2,2,3],
     * 
     * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
     * </pre>
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int current = 0;
        int i, j;
        for (i = 0; i < nums.length;) {
            for (j = i; j < nums.length; j++) {
                if (nums[j] != nums[i]) {
                    break;
                }

                if (j - i < 2) {
                    nums[current++] = nums[i];
                }
            }
            i = j;
        }

        return current;
    }
}
