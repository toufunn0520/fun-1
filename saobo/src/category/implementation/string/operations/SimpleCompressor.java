package category.implementation.string.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class SimpleCompressor {

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set.add("a5g");
        set.add("ab4g");
        set.add("ab3fg");
        set.add("abc2fg");
        set.add("abcdefg");
        set.add("abc1efg");
        String s = new SimpleCompressor().compress("abcdefg", set);
        System.out.println(s);
    }

    /**
     * Given a list of strings, compress that as short as possible but there should be no duplication in the compressed
     * list. i.e: abcde -> a3e, abcfe cannot be compressed to a3d any more since it has abcde mapping to it.
     * 
     * @param list
     * @return
     */
    public List<String> compress(List<String> list) {
        List<String> result = new ArrayList<String>();

        if (list == null || list.size() == 0) {
            return result;
        }

        Set<String> dedupedSet = new HashSet<String>();

        for (String s : list) {
            if (s != null && s.length() > 3) {
                String compressed = compress(s, dedupedSet);
                result.add(compressed);
            } else {
                result.add(s);
            }
        }

        return result;
    }

    public String compress(String s, Set<String> dedupedSet) {
        StringBuilder sb;

        int numOfExtraChar = 0;
        do {
            if (numOfExtraChar + 3 == s.length() && !dedupedSet.contains(s)) {
                return s;
            } else if (numOfExtraChar + 3 > s.length()) {
                throw new RuntimeException("cannot compress any more");
            }

            sb = new StringBuilder();
            sb.append(s.charAt(0));

            int numOfExtraInserted = 0;
            while (numOfExtraInserted < numOfExtraChar) {
                sb.insert(1 + numOfExtraInserted / 2, s.charAt(1 + numOfExtraInserted / 2));
                numOfExtraInserted++;

                if (numOfExtraInserted < numOfExtraChar) {
                    sb.insert(2 + numOfExtraInserted / 2, s.charAt(s.length() - ++numOfExtraInserted / 2 - 1));
                }
            }

            sb.insert(1 + (numOfExtraInserted + 1) / 2, s.length() - numOfExtraInserted - 2);
            sb.append(s.charAt(s.length() - 1));
            numOfExtraChar++;
        } while (dedupedSet.contains(sb.toString()));
        dedupedSet.add(sb.toString());
        return sb.toString();
    }

    @Test
    public void testWhenExtraInsertionNeeded() {
        List<String> input = Arrays.asList("abcdefg", "abadefg", "abhdefg", "abidefg", "abijkfg");

        List<String> result = new SimpleCompressor().compress(input);

        Assert.assertEquals(5, result.size());
        Assert.assertEquals("a5g", result.get(0));
        Assert.assertEquals("ab4g", result.get(1));
        Assert.assertEquals("ab3fg", result.get(2));
        Assert.assertEquals("ab3fg", result.get(2));
        Assert.assertEquals("abi2fg", result.get(3));
        Assert.assertEquals("abijkfg", result.get(4));
    }

    @Test
    public void testWhenNoExtraInsertionNeeded() {
        List<String> input = Arrays.asList("abcd", "efgh", "mnok");

        List<String> result = new SimpleCompressor().compress(input);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals("a2d", result.get(0));
        Assert.assertEquals("e2h", result.get(1));
        Assert.assertEquals("m2k", result.get(2));
    }
}
