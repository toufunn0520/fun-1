package category.math.number;

public class FactorialTrailingZeros {

    public static void main(String[] args) {
        System.out.println(new FactorialTrailingZeros().trailingZeroes(26));
    }

    /**
     * [Leetcode 172] https://leetcode.com/problems/factorial-trailing-zeroes/
     * 
     * <pre>
     * Given an integer n, return the number of trailing zeroes in n!.
     * </pre>
     * 
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int count = 0;
        for (int i = 5; (n / i) >= 1;) {
            count += n / i;
            n /= 5;
        }
        return count;
    }
}
