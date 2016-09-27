package category.dp.cache;

public class UglyNumber {

    public static void main(String[] args) {

        for (int i = 1; i < 20; i++)
            System.out.println(new UglyNumber().nthUglyNumber(i));

    }

    /**
     * [Leetcode 263] https://leetcode.com/problems/ugly-number/ Write a program to check whether a given number is an
     * ugly number. Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are
     * ugly while 14 is not ugly since it includes another prime factor 7. Note that 1 is typically treated as an ugly
     * number.
     *
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        if (num < 1) {
            return false;
        } else if (num == 1) {
            return true;
        }

        if (num % 2 == 0) {
            return isUgly(num >> 1);
        } else if (num % 3 == 0) {
            return isUgly(num / 3);
        } else if (num % 5 == 0) {
            return isUgly(num / 5);
        } else {
            return false;
        }
    }

    /**
     * [Leetcode 264] https://leetcode.com/problems/ugly-number-ii/
     * 
     * <pre>
     * Write a program to find the n-th ugly number. Ugly numbers are positive numbers whose prime factors only include
     * 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers. Note that 1 is
     * typically treated as an ugly number.
     * </pre>
     * 
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        if (n < 1) {
            return 0;
        }

        int[] uglyBase = { 2, 3, 5 };
        int[] uglyNumbers = new int[n];
        int currentIndex = 0;
        uglyNumbers[currentIndex++] = 1;
        int[] indice = new int[3];
        int[] nextNumber = { 2, 3, 5 };

        while (currentIndex < n) {
            int current = Math.min(Math.min(nextNumber[0], nextNumber[1]), nextNumber[2]);
            uglyNumbers[currentIndex++] = current;

            if (nextNumber[0] == current) {
                indice[0]++;
                nextNumber[0] = uglyBase[0] * uglyNumbers[indice[0]];
            }

            if (nextNumber[1] == current) {
                indice[1]++;
                nextNumber[1] = uglyBase[1] * uglyNumbers[indice[1]];
            }

            if (nextNumber[2] == current) {
                indice[2]++;
                nextNumber[2] = uglyBase[2] * uglyNumbers[indice[2]];
            }

        }

        return uglyNumbers[n - 1];
    }
}
