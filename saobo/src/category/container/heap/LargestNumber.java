package category.container.heap;

import java.util.PriorityQueue;

public class LargestNumber {

    private int combine(int num1, int num2) {
        String num1num2 = String.valueOf(num1) + String.valueOf(num2);
        String num2num1 = String.valueOf(num2) + String.valueOf(num1);

        for (int i = 0; i < num1num2.length(); i++) {
            if (num1num2.charAt(i) > num2num1.charAt(i)) {
                return -1;
            } else if (num1num2.charAt(i) < num2num1.charAt(i)) {
                return 1;
            }
        }

        return 0;
    }

    /**
     * [Leetcode 179] https://leetcode.com/problems/largest-number/
     * 
     * <pre>
     * Given a list of non negative integers, arrange them such that they form the largest number.
     * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
     * Note: The result may be very large, so you need to return a string instead of an integer.
     * </pre>
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(nums.length, (num1, num2) -> (combine(num1, num2)));

        for (int num : nums) {
            pq.add(num);
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int cur = pq.poll();

            if (sb.toString().equals("0") && cur == 0) {
                continue;
            } else {
                sb.append(cur);
            }
        }

        return sb.toString();
    }
}
