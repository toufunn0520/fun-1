package category.implementation.array;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {

    public static void main(String[] args) {
        int[] nums = { 100, 4, 200, 1, 300, 2, 0 };
        System.out.println(new LongestConsecutive().longestConsecutive(nums));
    }

    /**
     * [Leetcode 128] https://leetcode.com/problems/longest-consecutive-sequence/
     * 
     * <pre>
     * Given an unsorted array of integers, find the length of the longest
     * consecutive elements sequence. For example, Given [100, 4, 200, 1, 3, 2],
     * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its
     * length: 4. Your algorithm should run in O(n) complexity.
     * </pre>
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) {
            numSet.add(num);
        }

        int maxCount = 0;
        for (int num : nums) {
            if (numSet.contains(num)) {
                numSet.remove(num);

                int count = 1;
                int next = num + 1;
                while (numSet.contains(next)) {
                    numSet.remove(next);
                    next++;
                    count++;
                }

                int prev = num - 1;
                while (numSet.contains(prev)) {
                    numSet.remove(prev);
                    prev--;
                    count++;
                }

                maxCount = Math.max(maxCount, count);
            }
        }

        return maxCount;
    }
}
