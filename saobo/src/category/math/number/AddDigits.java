package category.math.number;

public class AddDigits {

    /**
     * [Leetcode 258] https://leetcode.com/problems/add-digits/
     * 
     * <pre>
     * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
     *
     * For example:
     * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
     *
     * Follow up:
     * Could you do it without any loop/recursion in O(1) runtime?
     * </pre>
     *
     * @param num
     * @return
     */
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        } else {
            return num % 9 == 0 ? 9 : num % 9;
        }
    }

}
