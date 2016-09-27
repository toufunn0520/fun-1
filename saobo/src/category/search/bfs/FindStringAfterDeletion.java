package category.search.bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class FindStringAfterDeletion {

    /**
     * [Google phone] 一个Dictionary，一个String，把String中去掉0或者任意多个character，得到一个字典中存在的String，求这样最长的String
     *
     * @param s
     * @param dictionary
     * @return
     */
    public static String findLongestSubstringAfterDeletion(String s, Set<String> dictionary) {
        if (s == null || s.length() == 0 || dictionary == null || dictionary.size() == 0) {
            return "";
        }

        Queue<String> queue = new LinkedList<String>();
        queue.add(s);
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                String current = queue.poll();
                if (dictionary.contains(current)) {
                    return current;
                } else {
                    for (int j = 0; j < current.length(); j++) {
                        queue.offer(current.substring(0, j) + current.substring(j + 1));
                    }
                }
            }
        }

        return "";
    }

    public static void main(String[] args) {
        String s = "abcdefg";

        Set<String> dict = new HashSet<String>(Arrays.asList("z"));
        System.out.println(findLongestSubstringAfterDeletion(s, dict));

    }

}
