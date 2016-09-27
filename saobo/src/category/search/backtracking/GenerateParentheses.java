package category.search.backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {
        List<String> result = new GenerateParentheses().generateParenthesis(3);

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

    }

    private void dfs(int leftCount, int rightCount, String current, List<String> results) {
        if (leftCount == 0 && rightCount == 0) {
            results.add(current);
        }

        if (leftCount > 0) {
            dfs(leftCount - 1, rightCount + 1, current + '(', results);
        }

        if (rightCount > 0) {
            dfs(leftCount, rightCount - 1, current + ')', results);
        }

    }

    /**
     * [Leetcode 22] https://leetcode.com/problems/generate-parentheses/
     * 
     * <pre>
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * 
     * For example, given n = 3, a solution set is:
     * 
     * "((()))", "(()())", "(())()", "()(())", "()()()"
     * </pre>
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<String>();

        dfs(n, 0, "", results);
        return results;
    }
}
