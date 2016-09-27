package category.twopointers.forward.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        String S = "barfoothefoobarman";
        String[] L = new String[] { "foo", "bar" };
        List<Integer> l = new SubstringWithConcatenationOfAllWords().findSubstring(S, L);
    }

    /**
     * [Leetcode 30] https://leetcode.com/problems/substring-with-concatenation-of-all-words/
     * 
     * <pre>
     * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices
     * of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening
     * characters. For example, given: s: "barfoothefoobarman" words: ["foo", "bar"] You should return the indices:
     * [0,9]. (order does not matter).
     * </pre>
     * 
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<Integer>();

        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return result;
        }

        Map<String, Integer> wordsMap = new HashMap<String, Integer>();
        for (String word : words) {
            wordsMap.put(word, wordsMap.containsKey(word) ? wordsMap.get(word) + 1 : 1);
        }

        int wordCount = words.length;
        int wordLength = words[0].length();

        for (int i = 0; i <= s.length() - wordLength * wordCount; i++) {
            Map<String, Integer> clonedWordsMap = new HashMap<String, Integer>(wordsMap);
            for (int j = 0; j < wordCount; j++) {

                String current = s.substring(i + j * wordLength, i + j * wordLength + wordLength);

                if (clonedWordsMap.containsKey(current)) {
                    int count = clonedWordsMap.get(current);
                    if (count == 1) {
                        clonedWordsMap.remove(current);
                    } else {
                        clonedWordsMap.put(current, count - 1);
                    }
                } else {
                    break;
                }

                if (j == wordCount - 1) {
                    result.add(i);
                }
            }
        }

        return result;
    }
}
