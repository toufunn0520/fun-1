package category.math.permutation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

public class PermutationSequence2 {

    public static void main(String[] args) {
        char[] orders = { 'u', 'f', 'o' };

        System.out.println(new PermutationSequence2().gerPermutationSequence("uuof", orders));
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

    public long gerPermutationSequence(String s, char[] orders) {
        if (s == null || s.length() == 0 || orders == null) {
            return 0;
        }

        int[] digits = convertStringToDigits(s, orders);

        int[] digitStatistics = getDigitStatistics(digits, orders.length + 1);
        int[] factorials = getFactorials(s.length());

        // this is an array with size one in order to be modified later in functions.
        long[] duplicateFactor = getDuplicateFactor(digitStatistics, factorials);

        TreeSet<ValueWithCount> digitSet = getDigitSet(digitStatistics);

        long sequence = 0;

        for (int i = 0; i < digits.length; i++) {
            sequence += getDigitSequence(digits[i], digitSet, duplicateFactor, digitStatistics, factorials,
                    digits.length - i);
        }

        return sequence + 1;
    }

    private long getDigitSequence(int digit, TreeSet<ValueWithCount> digitSet, long[] duplicateFactor,
            int[] digitStatistics, int[] factorails, int remainingDigits) {
        Iterator<ValueWithCount> it = digitSet.iterator();

        int sequence = 0;
        while (it.hasNext()) {
            ValueWithCount current = it.next();
            if (current.val == digit) {
                digitStatistics[current.val]--;
                if (current.count == 1) {
                    digitSet.remove(current);
                } else {
                    current.count--;
                    duplicateFactor[0] /= factorails[current.count + 1] * factorails[current.count];
                }

                return sequence;
            } else {
                int currentFrequence = digitStatistics[current.val];
                long currentDuplicateFactor = duplicateFactor[0] / factorails[currentFrequence]
                        * factorails[currentFrequence - 1];
                sequence += factorails[remainingDigits - 1] / currentDuplicateFactor;
            }
        }

        return -1;
    }

    private TreeSet<ValueWithCount> getDigitSet(int[] digitStatistics) {
        TreeSet<ValueWithCount> digitSet = new TreeSet<ValueWithCount>(
                (valueWithCount1, valueWithCount2) -> (Integer.compare(valueWithCount1.val, valueWithCount2.val)));

        for (int i = 0; i < digitStatistics.length; i++) {
            digitSet.add(new ValueWithCount(i, digitStatistics[i]));
        }

        return digitSet;
    }

    private int[] getDigitStatistics(int[] digits, int length) {
        int[] digitStatistics = new int[length];

        for (int digit : digits) {
            digitStatistics[digit]++;
        }

        return digitStatistics;
    }

    private long[] getDuplicateFactor(int[] digitStatistics, int[] factorials) {
        long[] duplicateFactor = { 1 };

        for (int digit : digitStatistics) {
            if (digit > 1) {
                duplicateFactor[0] *= factorials[digit];
            }
        }

        return duplicateFactor;
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
    public void testWithDuplciates() {
        char[] orders = { 'u', 'f', 'o' };
        PermutationSequence2 p = new PermutationSequence2();

        Assert.assertEquals(1, p.gerPermutationSequence("uufo", orders));
        Assert.assertEquals(2, p.gerPermutationSequence("uuof", orders));
        Assert.assertEquals(3, p.gerPermutationSequence("ufuo", orders));
        Assert.assertEquals(4, p.gerPermutationSequence("ufou", orders));
        Assert.assertEquals(5, p.gerPermutationSequence("uouf", orders));
        Assert.assertEquals(6, p.gerPermutationSequence("uofu", orders));

        char[] duplicatesOrder = { 'a', 'b' };
        Assert.assertEquals(1, p.gerPermutationSequence("aabb", duplicatesOrder));
        Assert.assertEquals(2, p.gerPermutationSequence("abab", duplicatesOrder));
        Assert.assertEquals(3, p.gerPermutationSequence("abba", duplicatesOrder));
        Assert.assertEquals(4, p.gerPermutationSequence("baab", duplicatesOrder));
        Assert.assertEquals(5, p.gerPermutationSequence("baba", duplicatesOrder));
        Assert.assertEquals(6, p.gerPermutationSequence("bbaa", duplicatesOrder));
    }

    @Test
    public void testWithNoDuplciates() {
        char[] orders = { 'u', 'f', 'o' };
        PermutationSequence2 p = new PermutationSequence2();

        Assert.assertEquals(1, p.gerPermutationSequence("ufo", orders));
        Assert.assertEquals(2, p.gerPermutationSequence("uof", orders));
        Assert.assertEquals(3, p.gerPermutationSequence("fuo", orders));
        Assert.assertEquals(4, p.gerPermutationSequence("fou", orders));
        Assert.assertEquals(5, p.gerPermutationSequence("ouf", orders));
        Assert.assertEquals(6, p.gerPermutationSequence("ofu", orders));

        char[] single = { 'a', 'b', 'c' };
        Assert.assertEquals(1, p.gerPermutationSequence("a", single));
        Assert.assertEquals(1, p.gerPermutationSequence("b", single));
        Assert.assertEquals(1, p.gerPermutationSequence("c", single));
    }
}

class ValueWithCount {

    int count;

    int val;

    public ValueWithCount(int val, int count) {
        this.val = val;
        this.count = count;
    }

    @Override
    public String toString() {
        return "val:" + val + " count:" + count;
    }
}
