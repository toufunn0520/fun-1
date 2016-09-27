package category.special;

public class MajorityElement {

    /**
     * [Leetcode 169] https://leetcode.com/problems/majority-element/
     * 
     * <pre>
     * Given an array of size n, find the majority element. The majority element is the element that appears more than [n/2] times.
     * You may assume that the array is non-empty and the majority element always exist in the array.
     * </pre>
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int current = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (current != nums[i]) {
                if (--count < 0) {
                    current = nums[i];
                    count = 1;
                }
            } else {
                count++;
            }
        }

        return current;
    }
}
