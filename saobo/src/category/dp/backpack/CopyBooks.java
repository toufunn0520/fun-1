package category.dp.backpack;

/**
 * Given an array A of integer with size of n( means n books and number of pages of each book) and k people to copy the
 * book. You must distribute the continuous id books to one people to copy. (You can give book A[1],A[2] to one people,
 * but you cannot give book A[1], A[3] to one people, because book A[1] and A[3] is not continuous.) Each person have
 * can copy one page per minute. Return the number of smallest minutes need to copy all the books.
 *
 * @author boyi
 */
public class CopyBooks {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * <pre>
     * 1. state: dp[i][nk] 表示前i本书用nk个人写的最小花费
     * 2. function: dp[i][nk] = Min(j=0->i) {Max {dp[j][nk - 1], w[j+1][i]}}
     * 3. Initialization: dp[i][1] = w[1][i];
     * 4. Answer: dp[n][k]
     * </pre>
     *
     * @param pages
     * @param k
     * @return
     */
    public int copyBooks(int[] pages, int k) {
        // write your code here
        int n = pages.length;
        int[][] w = init(pages);
        int[][] dp = new int[n + 2][k + 2];

        int ans = Integer.MIN_VALUE;
        if (n <= k) {
            for (int i = 0; i < n; i++)
                ans = Math.max(ans, pages[i]);
            return ans;
        }

        for (int i = 0; i <= n; ++i) {
            dp[i][1] = w[1][i];

        }

        for (int nk = 2; nk <= k; nk++) {

            for (int i = nk; i <= n; i++) {
                dp[i][nk] = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    if (dp[i][nk] == Integer.MAX_VALUE || dp[i][nk] > Math.max(dp[j][nk - 1], w[j + 1][i]))
                        dp[i][nk] = Math.max(dp[j][nk - 1], w[j + 1][i]);
                }
            }
        }
        return dp[n][k];
    }

    /**
     * @param pages
     *            : an array of integers
     * @param k
     *            : an integer
     * @return: an integer
     */
    int[][] init(int[] A) {
        int n = A.length;
        int[][] w = new int[n + 2][n + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                for (int k = i; k <= j; ++k) {
                    w[i][j] += A[k - 1];
                }
            }
        }
        return w;
    }
}
