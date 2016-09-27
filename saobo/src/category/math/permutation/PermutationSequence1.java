package category.math.permutation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

public class PermutationSequence1 {

    public static void main(String[] args) {
        char[] orders = { 'a', 'b', 'c' };

        System.out.println(new PermutationSequence1().gerPermutationSequence("aabc", orders));
    }

    private int[] convertStringToDigits(String s, char[] orders) {
        Map<Character, Integer> charToIndex = new HashMap<Character, Integer>();
        for (int i = 0; i < orders.length; i++) {
            charToIndex.put(orders[i], i + 1);
        }

        int[] digits = new int[s.length()];
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            digits[i] = charToIndex.get(charArray[i]);
        }

        return digits;
    }

    /**
     * Given a string and char orders, return the sequnce of the string of all the chars permutations. Assume the input
     * chars are unique and orders are valid for the string.
     *
     * @param s
     * @param orders
     * @return
     */
    public long gerPermutationSequence(String s, char[] orders) {
        if (s == null || s.length() == 0 || orders == null || s.length() != orders.length) {
            return 0;
        }

        // convert chars to integer so the tree set knows how to sort them
        int[] digits = convertStringToDigits(s, orders);

        TreeSet<Integer> digitSet = new TreeSet<Integer>();
        for (int digit : digits) {
            digitSet.add(digit);
        }

        int[] factorials = getFactorials(s.length());

        long sequence = 0;

        for (int i = 0; i < digits.length; i++) {
            int digitSequence = getDigitSequence(digits[i], digitSet);
            digitSet.remove(digits[i]);
            sequence += (digitSequence - 1) * factorials[digitSet.size()];
        }

        return sequence + 1;
    }

    private int getDigitSequence(int digit, TreeSet<Integer> digitSet) {
        Iterator<Integer> it = digitSet.iterator();

        int count = 1;
        while (it.hasNext()) {
            if (it.next() == digit) {
                return count;
            }
            count++;
        }

        return -1;
    }

    private int[] getFactorials(int number) {
        int[] factorials = new int[number + 1];
        factorials[0] = 1;

        for (int i = 1; i <= number; i++) {
            factorials[i] = i * factorials[i - 1];
        }

        return factorials;
    }

    @Test
    public void testWithCases() {
        char[] orders = { 'u', 'f', 'o' };
        PermutationSequence1 p = new PermutationSequence1();

        Assert.assertEquals(1, p.gerPermutationSequence("ufo", orders));
        Assert.assertEquals(2, p.gerPermutationSequence("uof", orders));
        Assert.assertEquals(3, p.gerPermutationSequence("fuo", orders));
        Assert.assertEquals(4, p.gerPermutationSequence("fou", orders));
        Assert.assertEquals(5, p.gerPermutationSequence("ouf", orders));
        Assert.assertEquals(6, p.gerPermutationSequence("ofu", orders));

        char[] single = { 'a' };
        Assert.assertEquals(1, p.gerPermutationSequence("a", single));
    }
}
