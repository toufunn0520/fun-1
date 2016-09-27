package category.container.hash;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    private static int getHappySum(int num) {
        int sum = 0;
        while (num != 0) {
            int digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        return sum;
    }

    /**
     * [Leetcode 202] https://leetcode.com/problems/happy-number/
     * 
     * <pre>
     * Write an algorithm to determine if a number is "happy".
     *
     * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by
     * the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops
     * endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
     *
     * Example: 19 is a happy number
     *
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     * </pre>
     *
     * @param num
     * @return
     */
    public boolean isHappy(int num) {
        if (num < 0)
            return false;

        Set<Integer> numSet = new HashSet<Integer>();

        while (num != 1) {
            if (numSet.add(num)) {
                num = getHappySum(num);
            } else {
                return false;
            }
        }
        return true;
    }
}
