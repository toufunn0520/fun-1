package category.math.number;

public class FindPairNumbers {

    /**
     * 给一个整数n，输出俩数x和y，使得x*y的值在 [n, n+2] 的范围内，同时保证 |x - y| 最小， e.g. n=25, return x=y=5 或 n=22， return x=4 y=6
     * 
     * @param n
     * @return
     */
    public static int[] findPairs(int n) {
        int[] result = new int[2];

        if (n <= 0) {
            return result;
        }

        int sqrtRoot1 = (int) Math.sqrt(n + 2);
        int sqrtRoot2 = (int) Math.sqrt(n + 2);

        while (true) {
            if (sqrtRoot1 * sqrtRoot2 >= n && sqrtRoot1 < n + 2) {
                result[0] = sqrtRoot1;
                result[1] = sqrtRoot2;
                break;
            } else if (sqrtRoot1 * sqrtRoot2 < n) {
                sqrtRoot1++;
            } else {
                sqrtRoot2--;
            }
        }

        return result;
    }
}
