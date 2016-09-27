package category.search.binarysearch;

public class Operations {

    public static void main(String[] args) {

        System.out.println(Math.abs(Integer.MIN_VALUE));

    }

    private double divide(double dividend, double divisor) {
        if (divisor > dividend) {
            return 0;
        }

        int quotient = 1;
        double tryDivisor = divisor;
        while (dividend / 2 > tryDivisor) {
            tryDivisor *= 2;
            quotient = quotient << 1;
        }

        return quotient + divide(dividend - tryDivisor, divisor);
    }

    /**
     * [Leetcode 29] https://leetcode.com/problems/divide-two-integers/
     *
     * <pre>
     * Divide two integers without using multiplication, division and mod operator.
     * If it is overflow, return MAX_INT.
     * </pre>
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }

        double quotient = divide(Math.abs((double) dividend), Math.abs((double) divisor));

        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            quotient = -1 * quotient;
        }

        if (quotient < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (quotient > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int) quotient;
        }
    }

    /**
     * [Leetcode 50] https://leetcode.com/problems/powx-n/
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {

        if (n < 0) {
            return 1.0 / pow(x, -n);
        } else {
            return pow(x, n);
        }
    }

    /**
     * [Leetcode 69] https://leetcode.com/problems/sqrtx/
     *
     * <pre>
     * Implement int sqrt(int x). Compute and return the square root
     * of x.
     * </pre>
     * 
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x <= 0) {
            return 0;
        }

        int left = 1;
        int right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    private double pow(double x, int n) {
        if (n == 0) {
            return 1;
        }

        double temp = pow(x, n / 2);

        if (n % 2 == 0) {
            return temp * temp;
        } else {
            return temp * temp * x;
        }
    }

}
