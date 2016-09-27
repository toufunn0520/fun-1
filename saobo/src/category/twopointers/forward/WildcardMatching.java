package category.twopointers.forward;

import org.junit.Assert;
import org.junit.Test;

public class WildcardMatching {

    public static void main(String[] args) {
        System.out.println(new WildcardMatching().isMatch("aab", "b.*"));
    }

    /**
     * [Leetcode 44] https://leetcode.com/problems/wildcard-matching/
     *
     * <pre>
     * Implement wildcard pattern matching with support for '?' and '*'.
     * 
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * 
     * The matching should cover the entire input string (not partial).
     * The function prototype should be: bool isMatch(const char *s, const char *p)
     * 
     * Some examples:
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "*") → true
     * isMatch("aa", "a*") → true
     * isMatch("ab", "?*") → true
     * isMatch("aab", "c*a*b") → false
     * </pre>
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String str, String pattern) {
        if (str == null && pattern == null) {
            return true;
        }

        if (str == null || pattern == null) {
            return false;
        }

        int lastStarIndex = -1;
        int lastMatchIndex = -1;
        int strIndex = 0, patternIndex = 0;

        while (strIndex < str.length()) {
            if (patternIndex < pattern.length()
                    && (str.charAt(strIndex) == pattern.charAt(patternIndex) || pattern.charAt(patternIndex) == '?')) {
                strIndex++;
                patternIndex++;
            } else if (patternIndex < pattern.length() && pattern.charAt(patternIndex) == '*') {
                lastStarIndex = patternIndex;
                lastMatchIndex = strIndex;
                patternIndex++;
            } else if (lastStarIndex != -1) {
                patternIndex = lastStarIndex + 1;
                strIndex = ++lastMatchIndex;
            } else {
                return false;
            }
        }

        while (patternIndex < pattern.length() && pattern.charAt(patternIndex) == '*') {
            patternIndex++;
        }

        return patternIndex == pattern.length();
    }

    @Test
    public void testWhenPatternContainsQuestionMark() {
        Assert.assertEquals(true, new WildcardMatching().isMatch("abc", "a?c"));
    }

    @Test
    public void testWhenPatternContainsStar() {
        Assert.assertEquals(true, new WildcardMatching().isMatch("abc", "a*"));
        Assert.assertEquals(true, new WildcardMatching().isMatch("abc", "a*c"));
        Assert.assertEquals(true, new WildcardMatching().isMatch("abc", "*"));
        Assert.assertEquals(true, new WildcardMatching().isMatch("abc", "?*"));
    }

    @Test
    public void testWhenPatternContainsTwoStars() {
        Assert.assertEquals(true, new WildcardMatching().isMatch("abdefefabc", "ab*ef*abc"));
        Assert.assertEquals(true, new WildcardMatching().isMatch("abdefefabc", "ab*efabc"));
    }

    @Test
    public void testWhenStrAndPatternAreEmpty() {
        Assert.assertEquals(true, new WildcardMatching().isMatch("", ""));
    }

    @Test
    public void testWhenStrAndPatternAreNull() {
        Assert.assertEquals(true, new WildcardMatching().isMatch(null, null));
    }

    @Test
    public void testWhenStrAndPatternOnlyOneIsNull() {
        Assert.assertEquals(false, new WildcardMatching().isMatch(null, "test"));
        Assert.assertEquals(false, new WildcardMatching().isMatch("test", null));
    }
}
