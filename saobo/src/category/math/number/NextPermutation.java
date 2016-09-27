package category.math.number;

public class NextPermutation {

    public static void main(String[] args) {
        int[] nums = { 2, 3, 1, 3, 3 };

        new NextPermutation().nextPermutation(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }
        System.out.println();

    }

    /**
     * [Leetcode 31] https://leetcode.com/problems/next-permutation/
     * 
     * <pre>
     * Implement next permutation, which rearranges numbers into the
     * lexicographically next greater permutation of numbers. If such
     * arrangement is not possible, it must rearrange it as the lowest possible
     * order (ie, sorted in ascending order). The replacement must be in-place,
     * do not allocate extra memory. Here are some examples. Inputs are in the
     * left-hand column and its corresponding outputs are in the right-hand
     * column.
     * 
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     * </pre>
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int start = 0;
        int end = nums.length - 1;
        int prev = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < prev) {
                int j = nums.length - 1;
                for (; j > i; j--) {
                    if (nums[j] > nums[i]) {
                        break;
                    }
                }

                swap(nums, i, j);
                start = i + 1;
                end = nums.length - 1;
                break;
            }
            prev = nums[i];
        }

        reverse(nums, start, end);

    }

    private void reverse(int[] nums, int start, int end) {
        int i = start, j = end;

        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
