package category.famousalgorithm;

import java.util.Arrays;

public class CountPrimes {

    public static void main(String[] args) {
        System.out.println(new CountPrimes().countPrimes(499979));

    }

    /**
     * [Leetcode 204] https://leetcode.com/problems/count-primes/
     * 
     * <pre>
     * Count the number of prime numbers less than a non-negative number, n. This is the implementation of Sieve of
     * Eratosthenes.
     * </pre>
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }

        boolean[] primesTag = new boolean[n];
        int[] primes = new int[n];
        Arrays.fill(primesTag, true);
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (primesTag[i]) {
                primes[count++] = i;
            }

            for (int j = 0; j < count && i * primes[j] < n; ++j) {
                primesTag[i * primes[j]] = false;
                // i.e 5 * 9 should be caculated with 15 * 3.
                if (i % primes[j] == 0) {
                    break;
                }
            }
        }

        return count;
    }

}
