package category.search.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder2 {

    public static void main(String[] args) {
        Set<String> dict = new HashSet<String>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        new WordLadder2().findLadders("hit", "cog", dict);

    }

    /**
     * Create a queue, add start to it and put start in dist map Initialize map with lists
     */
    void bfs(Map<String, List<String>> map, Map<String, Integer> word2Steps, String start, String end, Set<String> dict) {
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        dict.add(start); // make sure start and end in dictionary
        dict.add(end);
        word2Steps.put(start, 0);
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }

        while (!queue.isEmpty()) {
            String word = queue.poll();
            List<String> expansion = expand(word, dict); // generate all words
            for (String next : expansion) {
                map.get(next).add(word);
                if (!word2Steps.containsKey(next)) { // not in dist map yet
                    word2Steps.put(next, word2Steps.get(word) + 1);
                    queue.offer(next);
                }
            }
        }
    }

    /**
     * Add current word to first position. Add path to result if word is start
     */
    void dfs(List<List<String>> result, List<String> path, String word, String start, Map<String, Integer> word2Steps,
            Map<String, List<String>> child2Parent) {
        if (word.equals(start)) {
            path.add(0, word);
            result.add(new ArrayList<String>(path));
            path.remove(0);
            return; // note to return
        }
        for (String next : child2Parent.get(word)) {
            if (word2Steps.containsKey(next) && word2Steps.get(word) == word2Steps.get(next) + 1) { // backward, so word
                // = next + 1
                path.add(0, word); // add current word
                dfs(result, path, next, start, word2Steps, child2Parent); // dfs next word
                path.remove(0);
            }
        }
    }

    /**
     * Generate a list of words from the word. Skip if it's the same character. Adding to expansion list only if the
     * word is in the dictionary.
     */
    List<String> expand(String word, Set<String> dict) {
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < word.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                char[] chs = word.toCharArray();
                if (ch != chs[i]) {
                    chs[i] = ch;
                    String next = new String(chs);
                    if (dict.contains(next))
                        res.add(next);
                }
            }
        }
        return res;
    }

    /**
     * [Leetcode 126] https://leetcode.com/problems/word-ladder-ii/
     * 
     * <pre>
     * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
     *
     * Only one letter can be changed at a time
     * Each intermediate word must exist in the word list
     * For example,
     *
     * Given:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     * Return
     *   [
     *     ["hit","hot","dot","dog","cog"],
     *     ["hit","hot","lot","log","cog"]
     *   ]
     * Note:
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     * </pre>
     *
     * @param start
     * @param end
     * @param dict
     * @return
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<List<String>>();
        // used for generating the path.
        Map<String, List<String>> child2Parent = new HashMap<String, List<String>>();
        Map<String, Integer> word2Steps = new HashMap<String, Integer>();

        bfs(child2Parent, word2Steps, start, end, dict);
        dfs(result, new LinkedList<String>(), end, start, word2Steps, child2Parent);
        return result;
    }

}
