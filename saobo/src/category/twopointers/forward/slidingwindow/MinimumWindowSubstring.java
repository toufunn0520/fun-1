package category.twopointers.forward.slidingwindow;

import java.util.HashMap;

public class MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(minWindow("aadbc", "abc"));
    }

    /**
     * [Leetcode 76] https://leetcode.com/problems/minimum-window-substring/
     * 
     * <pre>
     * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
     *
     * For example,
     * S = "ADOBECODEBANC"
     * T = "ABC"
     * Minimum window is "BANC".
     *
     * Note:
     * If there is no such window in S that covers all characters in T, return the empty string "".
     *
     * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
     * </pre>
     *
     * @param S
     * @param T
     * @return
     */
    public static String minWindow(String S, String T) {
        if (S == null || S.length() == 0)
            return "";
        HashMap<Character, Integer> charsNeeded = new HashMap<Character, Integer>();
        for (int i = 0; i < T.length(); i++) {
            if (charsNeeded.containsKey(T.charAt(i))) {
                charsNeeded.put(T.charAt(i), charsNeeded.get(T.charAt(i)) + 1);
            } else {
                charsNeeded.put(T.charAt(i), 1);
            }
        }
        int left = 0;
        int count = 0;
        int minLen = S.length() + 1;
        int minStart = 0;
        for (int right = 0; right < S.length(); right++) {
            if (charsNeeded.containsKey(S.charAt(right))) {
                charsNeeded.put(S.charAt(right), charsNeeded.get(S.charAt(right)) - 1);
                if (charsNeeded.get(S.charAt(right)) >= 0) {
                    count++;
                }
                while (count == T.length()) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        minStart = left;
                    }
                    if (charsNeeded.containsKey(S.charAt(left))) {
                        charsNeeded.put(S.charAt(left), charsNeeded.get(S.charAt(left)) + 1);
                        if (charsNeeded.get(S.charAt(left)) > 0) {
                            count--;
                        }
                    }
                    left++;
                }
            }
        }
        if (minLen > S.length()) {
            return "";
        }
        return S.substring(minStart, minStart + minLen);
    }

}
