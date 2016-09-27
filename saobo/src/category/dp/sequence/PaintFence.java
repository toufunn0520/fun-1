package category.dp.sequence;

public class PaintFence {

    public static void main(String[] args) {
        System.out.println(numWays(1, 2));

    }

    /**
     * [Leetcode 276] https://leetcode.com/problems/paint-fence/
     * 
     * <pre>
     * There is a fence with n posts, each post can be painted with one of the k colors. You have to paint all the posts
     * such that no more than two adjacent fence posts have the same color. Return the total number of ways you can
     * paint the fence.
     * </pre>
     * 
     * @param n
     * @param k
     * @return
     */
    public static int numWays(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }

        if (n == 1) {
            return k;
        }

        int[] differentLastTwo = new int[n];
        int[] sameLastTwo = new int[n];

        differentLastTwo[0] = k;
        differentLastTwo[1] = k * (k - 1);
        sameLastTwo[0] = k;
        sameLastTwo[1] = k;

        for (int i = 2; i < n; i++) {
            differentLastTwo[i] = (sameLastTwo[i - 1] + differentLastTwo[i - 1]) * (k - 1);
            sameLastTwo[i] = differentLastTwo[i - 1];
        }

        return sameLastTwo[n - 1] + differentLastTwo[n - 1];
    }
}
