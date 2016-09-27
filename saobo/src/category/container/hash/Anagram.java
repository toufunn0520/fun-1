package category.container.hash;

import java.util.HashMap;
import java.util.Map;

public class Anagram {

    /**
     * [Leetcode 242] https://leetcode.com/problems/valid-anagram/
     * 
     * <pre>
     * Given two strings s and t, write a function to determine if t is an anagram of s.
     *
     * For example,
     * s = "anagram", t = "nagaram", return true.
     * s = "rat", t = "car", return false.
     *
     * Note:
     * You may assume the string contains only lowercase alphabets.
     * </pre>
     *
     * @param s
     * @param t
     * @return
     */
    public boolean validAnagram(String s, String t) {
        if (s == null && t == null) {
            return true;
        }

        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> charFrequence = new HashMap<Character, Integer>();
        for (char c : s.toCharArray()) {
            if (charFrequence.containsKey(c)) {
                charFrequence.put(c, charFrequence.get(c) + 1);
            } else {
                charFrequence.put(c, 1);
            }
        }

        for (char c : t.toCharArray()) {
            if (charFrequence.containsKey(c)) {
                if (charFrequence.get(c) == 1) {
                    charFrequence.remove(c);
                } else {
                    charFrequence.put(c, charFrequence.get(c) - 1);
                }
            } else {
                return false;
            }
        }

        return true;
    }
}
