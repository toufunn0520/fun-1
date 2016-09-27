package category.container.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    public static void main(String[] args) {

    }

    /**
     * [Leetcode 49] https://leetcode.com/problems/anagrams/
     *
     * <pre>
     * Given an array of strings, group anagrams together.
     * 
     * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Return:
     * 
     * [
     *   ["ate", "eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     * Note:
     * For the return value, each inner list's elements must follow the lexicographic order.
     * All inputs will be in lower-case.
     * </pre>
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();

        if (strs == null) {
            return result;
        }

        Map<String, List<String>> wordMap = new HashMap<String, List<String>>();

        for (String str : strs) {
            char[] word = str.toCharArray();
            Arrays.sort(word);
            String keyWord = new String(word);

            if (wordMap.containsKey(keyWord)) {
                wordMap.get(keyWord).add(str);
            } else {
                List<String> valueList = new ArrayList<String>();
                valueList.add(str);
                wordMap.put(keyWord, valueList);
            }
        }

        Iterator<List<String>> iterator = wordMap.values().iterator();
        while (iterator.hasNext()) {
            List<String> currentList = iterator.next();
            Collections.sort(currentList);
            result.add(currentList);
        }
        return result;
    }
}
