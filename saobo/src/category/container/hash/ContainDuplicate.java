package category.container.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ContainDuplicate {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 2312, 232, 232, 21, 4, 5, 1, 9, 11, 23, 213, 23, 1 };
        System.out.println(new ContainDuplicate().containsNearbyDuplicate(nums, 6));
    }

    /**
     * [Leetcode 217] https://leetcode.com/problems/contains-duplicate/
     *
     * <pre>
     * Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears
     * at least twice in the array, and it should return false if every element is distinct.
     * </pre>
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums == null) {
            return false;
        }

        Set<Integer> values = new HashSet<Integer>();

        for (int num : nums) {
            if (!values.add(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * [Leetcode 220] https://leetcode.com/problems/contains-duplicate-iii/
     * 
     * <pre>
     * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the
     * difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
     * </pre>
     * 
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2 || k < 1 || t < 0) {
            return false;
        }

        TreeSet<Integer> bst = new TreeSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];

            if ((bst.floor(current) != null && current <= t + bst.floor(current)) || bst.ceiling(current) != null
                    && bst.ceiling(current) - t <= current) {
                return true;
            }

            bst.add(current);

            if (bst.size() > k) {
                bst.remove(nums[i - k]);
            }
        }

        return false;
    }

    /**
     * [Leetcode 219] https://leetcode.com/problems/contains-duplicate-ii/
     * 
     * <pre>
     * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
     * such that nums[i] = nums[j] and the difference between i and j is at most k.
     * </pre>
     * 
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (numMap.containsKey(nums[i])) {
                int lastIndex = numMap.get(nums[i]);
                if (i - lastIndex <= k) {
                    return true;
                } else {
                    numMap.put(nums[i], i);
                }
            } else {
                numMap.put(nums[i], i);
            }
        }

        return false;
    }
}
