package category.math.number;

public class NumberOfDigitOne {

    public static void main(String[] args) {
        System.out.println(new NumberOfDigitOne().countDigitOne(1000));
    }

    /**
     * [Leetcode 233] https://leetcode.com/problems/number-of-digit-one/
     * 
     * <pre>
     * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
     *
     * For example:
     * Given n = 13,
     * Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
     * </pre>
     *
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        int ones = 0;
        for (long m = 1; m <= n; m *= 10)
            ones += (n / m + 8) / 10 * m + (n / m % 10 == 1 ? n % m + 1 : 0);
        return ones;
    }

}
