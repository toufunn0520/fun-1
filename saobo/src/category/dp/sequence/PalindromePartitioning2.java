package category.dp.sequence;

import java.util.Arrays;

public class PalindromePartitioning2 {

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioning2().minCut("aaba"));

    }

    /**
     * [Leetcode 132] https://leetcode.com/problems/palindrome-partitioning-ii/
     * 
     * <pre>
     * Given a string s, partition s such that every substring of the partition is a palindrome. Return the minimum cuts
     * needed for a palindrome partitioning of s. For example, given s = "aab", Return 1 since the palindrome
     * partitioning ["aa","b"] could be produced using 1 cut.
     * </pre>
     * 
     * @return
     */
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int len = s.length();
        boolean[][] palindromeFlag = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(palindromeFlag[i], false);
        }

        // minCut[i] : min cut from i to len-1
        int[] minCut = new int[len];

        for (int start = len - 1; start >= 0; start--) {
            minCut[start] = len - start - 1; // at most num-1 cuts
            for (int end = start; end < len; end++) {
                if (s.charAt(start) == s.charAt(end)) {
                    if (end - start < 2)
                        palindromeFlag[start][end] = true;
                    else
                        palindromeFlag[start][end] = palindromeFlag[start + 1][end - 1];
                }
                if (palindromeFlag[start][end]) {
                    if (end == len - 1)
                        minCut[start] = 0;
                    else
                        minCut[start] = Math.min(minCut[start], minCut[end + 1] + 1);
                }
            }
        }
        return minCut[0];
    }

}
