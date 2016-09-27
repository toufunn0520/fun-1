package category.search.backtracking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

    public static void main(String[] args) {
        Set<String> dict = new HashSet<String>();
        dict.add("hot");
        dict.add("dot");
        dict.add("lot");
        dict.add("dog");
        dict.add("log");

        System.out.println(new WordLadder().ladderLength("hit", "cog", dict));
    }

    /**
     * [Leetcode 127] https://leetcode.com/problems/word-ladder/
     * 
     * <pre>
     * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
     *
     * Only one letter can be changed at a time
     * Each intermediate word must exist in the word list
     * For example,
     *
     * Given:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * return its length 5.
     *
     * Note:
     * Return 0 if there is no such transformation sequence.
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     * </pre>
     *
     * @param beginWord
     * @param endWord
     * @param wordDict
     * @return
     */
    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        if (beginWord.equals(endWord)) {
            return 0;
        }

        wordDict.add(endWord);

        Queue<String> processingQueue = new LinkedList<String>();
        processingQueue.offer(beginWord);

        int length = 0;
        while (!processingQueue.isEmpty()) {
            int count = processingQueue.size();
            length++;

            for (int i = 0; i < count; i++) {
                String current = processingQueue.poll();

                if (current.equals(endWord)) {
                    return length;
                }

                char[] word = current.toCharArray();
                for (int j = 0; j < word.length; j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (word[j] == c) {
                            continue;
                        } else {
                            char[] newWord = word.clone();
                            newWord[j] = c;
                            String newStr = new String(newWord);

                            if (wordDict.contains(newStr)) {
                                processingQueue.offer(newStr);
                                wordDict.remove(newStr);
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

}
