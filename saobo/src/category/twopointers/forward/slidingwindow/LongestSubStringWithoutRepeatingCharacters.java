package category.twopointers.forward.slidingwindow;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class LongestSubStringWithoutRepeatingCharacters {

    /**
     * [Leetcode 3] https://leetcode.com/problems/longest-substring-without-repeating-characters/
     *
     * <pre>
     * Given a string, find the length of the longest substring without repeating characters. For example, the longest
     * substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring
     * is "b", with the length of 1.
     * </pre>
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }

        Map<Character, Integer> charMap = new HashMap<Character, Integer>();

        int maxLength = 0;
        int curLength = 0;
        int startIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charMap.containsKey(c) && i - charMap.get(c) <= curLength) {
                startIndex = charMap.get(c) + 1;
                curLength = i - startIndex + 1;
            } else {
                maxLength = Math.max(++curLength, maxLength);
            }
            charMap.put(c, i);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(LongestSubStringWithoutRepeatingCharacters.lengthOfLongestSubstring("tmmzuxt"));
    }

    @Test
    public void testAllRepeatsOne() {
        Assert.assertEquals(1, lengthOfLongestSubstring("aaaaaaaaa"));
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcabc"));
        Assert.assertEquals(3, lengthOfLongestSubstring("abccc"));
        Assert.assertEquals(6, lengthOfLongestSubstring("aefaebcd"));
        Assert.assertEquals(6, lengthOfLongestSubstring("aebcedaf"));
    }

    @Test
    public void testNullEmpty() {
        Assert.assertEquals(0, lengthOfLongestSubstring(null));
        Assert.assertEquals(0, lengthOfLongestSubstring(""));
    }

}
