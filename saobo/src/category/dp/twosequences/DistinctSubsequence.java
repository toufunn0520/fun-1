package category.dp.twosequences;

import java.util.Arrays;

public class DistinctSubsequence {

    public static void main(String[] args) {
        System.out.println(new DistinctSubsequence().numDistinct("rabbbit", "rabbit"));

    }

    /**
     * [Leetcode 115] https://leetcode.com/problems/distinct-subsequences/
     * 
     * <pre>
     * Given a string S and a string T, count the number of distinct subsequences of T in S. A subsequence of a string
     * is a new string which is formed from the original string by deleting some (can be none) of the characters without
     * disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC"
     * is not). Here is an example: S = "rabbbit", T = "rabbit" Return 3.
     * </pre>
     * 
     * @param S
     * @param T
     * @return
     */
    int numDistinct(String S, String T) {
        if (S.length() < T.length())
            return 0;

        int[] match = new int[S.length()];
        Arrays.fill(match, 0);
        match[0] = 1;
        for (int i = 1; i <= S.length(); i++) {
            for (int j = T.length(); j >= 1; j--) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    match[j] += match[j - 1];
                }
            }
        }
        return match[T.length()];
    }
}
