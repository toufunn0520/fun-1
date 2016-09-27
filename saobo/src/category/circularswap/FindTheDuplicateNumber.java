package category.circularswap;

public class FindTheDuplicateNumber {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * [Leetcode 287] https://leetcode.com/problems/find-the-duplicate-number/
     *
     * <pre>
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist.
     * Assume that there is only one duplicate number, find the duplicate one.
     * 
     * Note:
     * You must not modify the array (assume the array is read only).
     * You must use only constant, O(1) extra space.
     * Your runtime complexity should be less than O(n2).
     * There is only one duplicate number in the array, but it could be repeated more than once.
     * </pre>
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        if (nums == null) {
            throw new RuntimeException();
        }

        if (nums.length == 0)
            return 0;
        int slow = 0, fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while (slow != fast) {
            if (slow == nums[slow])
                return slow;
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            if (slow == nums[slow])
                return slow;
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

}
