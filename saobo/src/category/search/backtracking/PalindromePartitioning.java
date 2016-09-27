package category.search.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    public static void main(String[] args) {
        new PalindromePartitioning().partition("a");
    }

    private boolean isPalindrome(String s) {
        if (s.length() < 2) {
            return true;
        }

        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }

        return true;
    }

    /**
     * [Leetcode 131] https://leetcode.com/problems/palindrome-partitioning/
     * 
     * <pre>
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     *
     * Return all possible palindrome partitioning of s.
     *
     * For example, given s = "aab",
     * Return
     *
     *   [
     *     ["aa","b"],
     *     ["a","a","b"]
     *   ]
     * </pre>
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> results = new ArrayList<List<String>>();

        List<String> result = new ArrayList<String>();
        if (s == null) {
            return results;
        }

        partitionHelper(results, result, s);

        return results;
    }

    private void partitionHelper(List<List<String>> results, List<String> result, String s) {
        if (s.length() == 0) {
            results.add(new ArrayList<String>(result));
            return;
        }

        for (int i = 1; i <= s.length(); i++) {
            String subString = s.substring(0, i);
            if (isPalindrome(subString)) {
                result.add(subString);
                partitionHelper(results, result, s.substring(i));
                result.remove(result.size() - 1);
            }
        }
    }

}
