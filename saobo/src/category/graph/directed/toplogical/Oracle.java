package category.graph.directed.toplogical;

import interview.utils.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import category.graph.directed.Graph;

public class Oracle {

    public static void main(String[] args) {
        List<String> words = Arrays.asList(null, "shua", "ti", "na", "offergoogle", "offerfacebook", "offerfacezyga",
                "bigoffer");

        ListUtils.listPrint(new Oracle().findOrder(words));
    }

    /**
     * [Twitter phone] There is an oracle with list of words sorted by some rule, figure out the order of characters.
     *
     * @param words
     * @return
     */
    public List<Character> findOrder(List<String> words) {
        List<Character> orders = new ArrayList<Character>();
        Graph wordGraph = new Graph();

        if (words == null || words.size() == 0) {
            return orders;
        }

        int startIndex = 0;
        String prev = words.get(0);
        while (words.get(startIndex) == null) {
            startIndex++;
            prev = words.get(startIndex);
        }

        for (int i = startIndex + 1; i < words.size(); i++) {
            String current = words.get(i);

            int j = 0;
            while (j < prev.length() && j < current.length()) {
                if (prev.charAt(j) == current.charAt(j)) {
                    j++;
                    if (j == prev.length() && prev.length() < current.length()) {
                        wordGraph.addEdge(' ', current.charAt(j));
                    } else if (j == current.length() && prev.length() > current.length()) {
                        wordGraph.addEdge(prev.charAt(j), ' ');
                    }
                } else {
                    wordGraph.addEdge(prev.charAt(j), current.charAt(j));
                    break;
                }
            }

            prev = current;
        }

        return wordGraph.topologicalSort();
    }

    @Test
    public void testWhenNoWords() {
        Assert.assertEquals(new ArrayList<Character>(), new Oracle().findOrder(null));
        Assert.assertEquals(new ArrayList<Character>(), new Oracle().findOrder(new ArrayList<String>()));
    }

    @Test
    public void testWithMultipleDifferentWords() {
        List<String> words = Arrays.asList("ac", "ec", "bbc", "bdc", "bdca");

        Assert.assertEquals(Arrays.asList(' ', 'a', 'e', 'b', 'd'), new Oracle().findOrder(words));
    }

    @Test
    public void testWithNullWords() {
        List<String> words = Arrays.asList(null, null, "abc", "b");

        Assert.assertEquals(Arrays.asList('a', 'b'), new Oracle().findOrder(words));
    }

    @Test
    public void testWithOneWord() {
        List<String> words = Arrays.asList("abc");

        Assert.assertEquals(new ArrayList<Character>(), new Oracle().findOrder(words));
    }

    @Test
    public void testWithTwoDifferentWords() {
        List<String> words = Arrays.asList("abc", "b");

        Assert.assertEquals(Arrays.asList('a', 'b'), new Oracle().findOrder(words));
    }

    @Test
    public void testWithTwoDifferentWordsWithBlankCompare() {
        List<String> words = Arrays.asList("abb", "ab", "b", "bc");

        Assert.assertEquals(Arrays.asList('a', 'b', ' ', 'c'), new Oracle().findOrder(words));
    }

    @Test
    public void testWithTwoDifferentWordsWithDuplicates() {
        List<String> words = Arrays.asList("abb", "abb", "abz", "b");

        Assert.assertEquals(Arrays.asList('a', 'b', 'z'), new Oracle().findOrder(words));
    }

    @Test
    public void testWithTwoDifferentWordsWithMultipleOptions() {
        List<String> words = Arrays.asList("shua", "offergoogle", "offerfacebook", "offerfacezyga", "bigoffer");
        Map<Character, Integer> charToId = new HashMap<Character, Integer>();
        List<Character> results = new Oracle().findOrder(words);
        for (int i = 0; i < results.size(); i++) {
            charToId.put(results.get(i), i);
        }

        Assert.assertTrue(charToId.get('s') < charToId.get('o'));
        Assert.assertTrue(charToId.get('o') < charToId.get('b'));
        Assert.assertTrue(charToId.get('g') < charToId.get('f'));
        Assert.assertTrue(charToId.get('b') < charToId.get('z'));
    }

    @Test
    public void testWithTwoDifferentWordsWithSameOrDifferentPrefixMixed() {
        List<String> words = Arrays.asList("abb", "abz", "b");

        Assert.assertEquals(Arrays.asList('a', 'b', 'z'), new Oracle().findOrder(words));
    }

    @Test
    public void testWithTwoDifferentWordsWithSamePrefix() {
        List<String> words = Arrays.asList("abc", "abz");

        Assert.assertEquals(Arrays.asList('c', 'z'), new Oracle().findOrder(words));
    }

    @Test
    public void testWithTwoIdenticalWords() {
        List<String> words = Arrays.asList("abc", "abc");

        Assert.assertEquals(new ArrayList<Character>(), new Oracle().findOrder(words));
    }
}
