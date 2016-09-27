package category.container.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Isomorphic {

    public static void main(String[] args) {
        System.out.println(new Isomorphic().isIsomorphic("ab", "aa"));
    }

    /**
     * [Leetcode 205] https://leetcode.com/problems/isomorphic-strings/
     * 
     * <pre>
     * Given two strings s and t, determine if they are isomorphic.
     * Two strings are isomorphic if the characters in s can be replaced to get t.
     * All occurrences of a character must be replaced with another character while preserving the order of characters.
     * No two characters may map to the same character but a character may map to itself.
     *
     * For example,
     * Given "egg", "add", return true.
     * Given "foo", "bar", return false.
     * Given "paper", "title", return true.
     *
     * Note:
     * You may assume both s and t have the same length.
     * </pre>
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) {
            return true;
        }

        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        Map<Character, Character> map = new HashMap<Character, Character>();
        Set<Character> set = new HashSet<Character>();

        for (int i = 0; i < s.length(); i++) {
            char chars = s.charAt(i);
            char chart = t.charAt(i);

            if (map.containsKey(chars)) {
                if (map.get(chars) != chart) {
                    return false;
                }
            } else if (set.contains(chart)) {
                return false;
            } else {
                map.put(chars, chart);
                set.add(chart);
            }
        }
        return true;
    }

}
