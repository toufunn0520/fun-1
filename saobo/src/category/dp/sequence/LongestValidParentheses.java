package category.dp.sequence;

import java.util.Stack;

public class LongestValidParentheses {

    /**
     * [Leetcode 32] https://leetcode.com/problems/longest-valid-parentheses/
     *
     * <pre>
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
     * parentheses substring. For "(()", the longest valid parentheses substring is "()", which has length = 2. Another
     * example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     *
     * state:
     *  validLength[i] : 以s[i-1]为结尾的longest valid parentheses substring的长度。
     *
     * function
     *  s[i] = ')'：找i前一个字符的最长括号串DP[i]的前一个字符j = i-2-DP[i-1]
     *  DP[i] = DP[i-1] + 2 + DP[j]，如果j >=0，且s[j] = '('
     *  DP[i] = 0，如果j<0，或s[j] = ')'
     *
     * ......... (     x    x    x    x   )
     *           j                   i-2 i-1
     * </pre>
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int len = s.length(), maxLen = 0;
        int[] validLength = new int[len + 1];

        for (int i = 1; i <= len; i++) {
            int j = i - 2 - validLength[i - 1];
            if (s.charAt(i - 1) == '(' || j < 0 || s.charAt(j) == ')') {
                validLength[i] = 0;
            } else {
                validLength[i] = validLength[i - 1] + 2 + validLength[j];
                maxLen = Math.max(maxLen, validLength[i]);
            }
        }

        return maxLen;
    }

    public static int longestValidParenthesesStack(String s) {
        if (s == null || s.length() == 0)
            return 0;

        Stack<Integer> stack = new Stack<Integer>(); // Save indices of '('
        int[] dp = new int[s.length()]; // Store the length of the current
        // longest valid sequence.

        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push(i);
            else if (stack.isEmpty())
                continue;
            else if (stack.peek() > 0)
                dp[i] = 2 + dp[stack.pop() - 1] + dp[i - 1]; // connect two
            // valid sequences,
            // or increase the
            // length of
            // current valid
            // sequence.
            else {
                dp[i] = 2 + dp[i - 1]; // leftmost char is a '('
                stack.pop();
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
