package category.container.hash;

import java.util.HashMap;
import java.util.Map;

public class ParlindromePermutation {

    /**
     * [Leetcode 266] https://leetcode.com/problems/palindrome-permutation/
     * 
     * <pre>
     * Given a string, determine if a permutation of the string could form a palindrome. For example, "code" -> False,
     * "aab" -> True, "carerac" -> True.
     * </pre>
     *
     * @param s
     * @return
     */
    public static boolean canPermutePalindrome(String s) {
        if (s == null) {
            return true;
        }

        char[] chars = s.toCharArray();
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (charMap.containsKey(chars[i])) {
                charMap.put(chars[i], charMap.get(chars[i]) + 1);
            } else {
                charMap.put(chars[i], 1);
            }
        }

        boolean canBeOdd = true;
        for (Integer i : charMap.values()) {
            if (i % 2 != 0) {
                if (canBeOdd) {
                    canBeOdd = false;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(canPermutePalindrome("aa"));
    }

    /**
     * Same idea, just use array instead of hashmap.
     *
     * @param str
     * @return
     */
    public int isAnagramAPalindrome(String str) {
        if (str == null || str.isEmpty()) {
            return 1;
        }

        int[] chars = new int[26];

        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a';
            chars[index]++;
        }

        int oddNumber = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] % 2 == 1) {
                oddNumber++;
            }
        }

        if (oddNumber > 1) {
            return 0;
        } else {
            return 1;
        }

    }
}
