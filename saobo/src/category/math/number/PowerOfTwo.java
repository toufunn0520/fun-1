package category.math.number;

public class PowerOfTwo {

    /**
     * [Leetcode 231] https://leetcode.com/problems/power-of-two/
     * 
     * <pre>
     * Given an integer, write a function to determine if it is a power of two.
     * </pre>
     * 
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        if (n == 1) {
            return true;
        }

        while (n > 0) {
            if (n % 2 != 0 && n != 1) {
                return false;
            } else {
                n = n / 2;
            }
        }

        return true;
    }
}
