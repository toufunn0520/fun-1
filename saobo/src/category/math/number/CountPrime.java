package category.math.number;

import java.util.Arrays;

public class CountPrime {

    public static void main(String[] args) {
        System.out.println(new CountPrime().countPrimes(1000));
    }

    /**
     * Count the number of prime numbers less than a non-negative number, n.
     * 
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }

        boolean[] tag = new boolean[n];
        int[] primes = new int[n];
        Arrays.fill(tag, true);
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (tag[i]) {
                primes[count++] = i;
            }

            for (int j = 0; j < count && i * primes[j] < n; ++j) {
                tag[i * primes[j]] = false;
                if (i % primes[j] == 0) {
                    break;
                }
            }
        }

        return count;
    }
}
