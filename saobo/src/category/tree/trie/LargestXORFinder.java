package category.tree.trie;

import org.junit.Assert;
import org.junit.Test;

public class LargestXORFinder {

    private static String getBinaryString(int number) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(number));

        while (sb.length() < 31) {
            sb.insert(0, '0');
        }

        return sb.toString();
    }

    /**
     * Try to flip from highest bit to the lowest until find one.
     *
     * @param number
     * @param trie
     * @return
     */
    private static int getLargestXOR(int number, Trie trie) {

        int mask = Integer.MAX_VALUE;
        int pairNumber = number ^ mask;
        String pairNumberString = getBinaryString(pairNumber);

        String closestMathString = trie.closestMatchBinaryString(pairNumberString);
        int closedMathNumber = Integer.parseInt(closestMathString, 2);

        return number ^ closedMathNumber;
    }

    /**
     * [Google onsite]: Find the largest XOR of any two positive numbers in an array. Tag: search result from most
     * optimal result.
     *
     * @param numbers
     * @return
     */
    public static int getLargestXOR(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            throw new IllegalArgumentException("input is not valid");
        }

        Trie trie = new Trie();
        for (int number : numbers) {
            String current = getBinaryString(number);
            trie.insert(current);
        }

        int maxXOR = 0;
        for (int number : numbers) {
            int xor = getLargestXOR(number, trie);
            maxXOR = Math.max(xor, maxXOR);
        }

        return maxXOR;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, Integer.MAX_VALUE };
        System.out.println(getLargestXOR(nums));
    }

    @Test
    public void test() {
        int[] nums1 = { Integer.MAX_VALUE & 4, 1, Integer.MAX_VALUE, 1 << 30 };
        int[] nums2 = new int[1 << 15];
        for (int i = 0; i < 1 << 15; i++) {
            nums2[i] = Integer.MAX_VALUE - i;
        }

        Assert.assertEquals(Integer.MAX_VALUE - 1, getLargestXOR(nums1));
        Assert.assertEquals((Integer.MAX_VALUE & 1 << 15) - 1, getLargestXOR(nums2));
    }
}
