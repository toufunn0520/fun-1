package category.dp.twosequences;

public class InterleavingString {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * [Leetcode 97] https://leetcode.com/problems/interleaving-string/
     * 
     * <pre>
     * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
     *
     * For example,
     * Given:
     * s1 = "aabcc",
     * s2 = "dbbca",
     *
     * When s3 = "aadbbcbcac", return true.
     * When s3 = "aadbbbaccc", return false.
     * </pre>
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3 == null || s2 == null || s3 == null || s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean[][] match = new boolean[s2.length() + 1][s1.length() + 1];
        match[0][0] = true;
        for (int j = 1; j <= s1.length(); j++) {
            match[0][j] = match[0][j - 1] && (s1.charAt(j - 1) == s3.charAt(j - 1));
        }
        for (int i = 1; i <= s2.length(); i++) {
            match[i][0] = match[i - 1][0] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        }

        for (int i = 1; i <= s2.length(); i++) {
            for (int j = 1; j <= s1.length(); j++) {
                match[i][j] = match[i - 1][j] && (s2.charAt(i - 1) == s3.charAt(i + j - 1)) || match[i][j - 1]
                        && (s1.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return match[s2.length()][s1.length()];
    }

}
