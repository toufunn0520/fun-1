package category.implementation.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SortBySwapOneChar {

    public static void main(String[] args) {
        char[] chars = "lmncdabijkefgh".toCharArray();

        sort(chars);

        for (char c : chars) {
            System.out.print(c + " ");
        }

    }

    /**
     * [Google onsite] Given an array of chars, sort them by only swapping with char a each time.
     *
     * @param chars
     */
    public static void sort(char[] chars) {
        if (chars == null || chars.length == 0) {
            return;
        }

        Map<Character, Integer> char2Index = new HashMap<Character, Integer>();
        Set<Character> unsortedChars = new HashSet<Character>();

        for (int i = 0; i < chars.length; i++) {
            char2Index.put(chars[i], i);

            if (chars[i] != 'a' + i) {
                unsortedChars.add(chars[i]);
            }
        }

        while (unsortedChars.size() > 0) {
            while (unsortedChars.remove((char) ('a' + char2Index.get('a')))) {
                swap(chars, char2Index, char2Index.get((char) ('a' + char2Index.get('a'))), char2Index.get('a'));
            }

            if (!unsortedChars.isEmpty()) {
                char next = unsortedChars.iterator().next();
                swap(chars, char2Index, 0, char2Index.get(next));
                unsortedChars.add('a');
                unsortedChars.add(next);
            }
        }
    }

    private static void swap(char[] chars, Map<Character, Integer> char2Index, int index1, int index2) {
        char tempChar = chars[index1];
        chars[index1] = chars[index2];
        chars[index2] = tempChar;

        char2Index.put(chars[index2], index2);
        char2Index.put(chars[index1], index1);
    }
}
