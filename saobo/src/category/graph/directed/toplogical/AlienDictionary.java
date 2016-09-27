package category.graph.directed.toplogical;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AlienDictionary {

    public static void main(String[] args) {
        String[] dic = { "wrt", "wrf", "er", "ett", "rftt" };
        System.out.println(new AlienDictionary().alienOrder(dic));

    }

    /**
     * [Leetcode 269] https://leetcode.com/problems/alien-dictionary/
     * 
     * <pre>
     * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
     * You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language.
     * Derive the order of letters in this language.
     *
     * For example,
     * Given the following words in dictionary,
     *
     * [
     *   "wrt",
     *   "wrf",
     *   "er",
     *   "ett",
     *   "rftt"
     * ]
     * The correct order is: "wertf".
     *
     * Note:
     * You may assume all letters are in lowercase.
     * If the order is invalid, return an empty string.
     * There may be multiple valid order of letters, return any one of them is fine.
     * </pre>
     *
     * @param words
     * @return
     */
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        Map<Character, List<Character>> char2precedences = new HashMap<Character, List<Character>>();
        Set<Character> singles = new HashSet<Character>();

        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                singles.add(c);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words[i].length() && j < words[i + 1].length(); j++) {
                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    if (!char2precedences.containsKey(words[i + 1].charAt(j))) {
                        List<Character> pres = new LinkedList<Character>();
                        pres.add(words[i].charAt(j));
                        char2precedences.put(words[i + 1].charAt(j), pres);
                    } else {
                        char2precedences.get(words[i + 1].charAt(j)).add(words[i].charAt(j));
                    }
                    singles.remove(words[i + 1].charAt(j));
                    singles.remove(words[i].charAt(j));
                    break;
                }
            }
        }

        for (Character single : singles) {
            sb.append(single);
        }

        Queue<Character> queue = new LinkedList<Character>();
        Set<Character> visited = new HashSet<Character>();

        for (char current : char2precedences.keySet()) {
            Set<Character> currentPath = new HashSet<Character>();
            if (!toplogicalSortUtil(char2precedences, current, queue, visited, currentPath)) {
                return "";
            }
        }

        while (!queue.isEmpty()) {
            sb.append(queue.poll());
        }

        return sb.toString();
    }

    private boolean toplogicalSortUtil(Map<Character, List<Character>> char2precedence, char current,
            Queue<Character> queue, Set<Character> visited, Set<Character> currentPath) {
        if (!currentPath.add(current)) {
            return false;
        }

        if (!visited.add(current)) {
            return true;
        }

        if (char2precedence.containsKey(current)) {
            for (char c : char2precedence.get(current)) {
                if (!toplogicalSortUtil(char2precedence, c, queue, visited, currentPath)) {
                    return false;
                }
            }
        }

        queue.offer(current);
        return true;
    }

}
