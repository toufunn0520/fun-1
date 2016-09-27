package category.dp.sequence;

import java.util.Arrays;

public class NumSquares {

    public static void main(String[] args) {
        System.out.println(numSquares(6));
    }

    /**
     * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which
     * sum to n. For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
     *
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }

        int[] count = new int[n + 1];
        Arrays.fill(count, Integer.MAX_VALUE);

        for (int i = 1; i * i <= n; i++) {
            count[i * i] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; i + j * j <= n; j++) {
                count[i + j * j] = Math.min(count[i] + 1, count[i + j * j]);
            }
        }

        return count[n];
    }
}
