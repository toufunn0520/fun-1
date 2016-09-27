package category.dp.twosequences;

public class RegularExpressionMathcing {

    /**
     * [Leetcode 10] https://leetcode.com/problems/regular-expression-matching/
     *
     * <pre>
     * Implement regular expression matching with support for '.' and '*'.
     *
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     *
     * The matching should cover the entire input string (not partial).
     *
     * The function prototype should be:
     * bool isMatch(const char *s, const char *p)
     *
     * Some examples:
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "a*") → true
     * isMatch("aa", ".*") → true
     * isMatch("ab", ".*") → true
     * isMatch("aab", "c*a*b") → true
     * </pre>
     *
     * @param str
     * @param pattern
     * @return
     */
    public boolean isMatch(String str, String pattern) {
        int stringLength = str.length();
        int patternLength = pattern.length();

        // matchFlag[i][j] means str i-1 mathes pattern j - 1
        boolean[][] mathFlag = new boolean[stringLength + 1][patternLength + 1];

        /**
         * *
         *
         * <pre>
         * dp[i][j] stands for whether s[0:i-1] matches p[0:j-1]
         * Formula：Because only p will have regular expression，so we will analysis based on p.charAt(j - 1)
         * p.charAt(j-1) != '.' && p.charAt(j-1) != '*'：dp[i][j] = dp[i-1][j-1] && (s.charAt(i-1) == p.charAt(j-1))
         * p.charAt(j-1) == '.'：dp[i][j] = dp[i-1][j-1]
         * The key point is when p.charAt(j-1) = '*'。Because '*' can match 0，1，or more p.charAt(j - 2)。
         *
         * 1. Match 0 element，remove p.charAt(j-2)，now p[0: j-1] = p[0: j-3]
         *      dp[i][j] = dp[i][j-2]
         * 2. Match 1 element，here we have p[0: j-1] = p[0: j-2]
         *      dp[i][j] = dp[i][j-1]
         * 3. Match 2+ elements，here p[0: j-1] = { p[0: j-2], p[j-2], ... , p[j-2] }
         *      dp[i][j] = dp[i-1][j] && (p[j-2]=='.' || s[i-2]==p[j-2])
         * </pre>
         */
        mathFlag[0][0] = true;
        for (int i = 0; i <= stringLength; i++) {
            for (int j = 1; j <= patternLength; j++) {
                if (pattern.charAt(j - 1) != '*') {
                    mathFlag[i][j] = i > 0 && mathFlag[i - 1][j - 1]
                            && (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.');
                } else {
                    mathFlag[i][j] = mathFlag[i][j - 2]
                            || mathFlag[i][j - 1]
                            || (i > 0 && mathFlag[i - 1][j] && (str.charAt(i - 1) == pattern.charAt(j - 2) || pattern
                            .charAt(j - 2) == '.'));
                }
            }
        }

        return mathFlag[stringLength][patternLength];
    }
}
