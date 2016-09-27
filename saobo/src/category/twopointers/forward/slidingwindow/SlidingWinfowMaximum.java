package category.twopointers.forward.slidingwindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWinfowMaximum {

    public static void main(String[] args) {
        int[] nums = { 10, 7, 2, 4, 5, 10 };

        System.out.println(Arrays.toString(new SlidingWinfowMaximum().maxSlidingWindowOpt(nums, 2)));
    }

    /**
     * [Leetcode 239] https://leetcode.com/problems/sliding-window-maximum/
     * 
     * <pre>
     * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
     *
     * For example,
     * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
     *
     * Window position                Max
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     * Therefore, return the max sliding window as [3,3,5,5,6,7].
     *
     * Note:
     * You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
     * </pre>
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        Deque<Integer> deque = new LinkedList<Integer>();
        int[] result = new int[nums.length - k + 1];

        int i = 0;

        for (i = 0; i < nums.length; i++) {
            int current = nums[i];
            while (deque.size() > 0 && current > deque.getLast()) {
                deque.pollLast();
            }
            while (deque.size() < k && deque.size() < i + 1) {
                deque.addLast(current);
            }

            if (i - k + 1 >= 0) {
                result[i - k + 1] = deque.peekFirst();
                deque.pollFirst();
            }
        }

        return result;
    }

    public int[] maxSlidingWindowOpt(int[] nums, int k) {
        if (k == 0) {
            return new int[] {};
        }

        int[] max = new int[nums.length - k + 1];

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.getLast()] <= nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);

            if (i + 1 >= k) {
                max[i + 1 - k] = nums[deque.getFirst()];

                if (i + 1 - k >= deque.getFirst()) {
                    deque.removeFirst();
                }
            }
        }

        return max;
    }

}
