package category.dp.twosequences;

import org.junit.Assert;
import org.junit.Test;

/**
 * Insert minimal chars to a string to make the string a palindrome.
 */
public class ShortestPalindromeAfterInsertion {

    public static int calculate(String s) {
        if (s == null || s.length() == 1) {
            return 0;
        }

        int[][] result = new int[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            result[i][i] = 0;
        }

        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; i + j < s.length(); j++) {
                result[j][i + j] = Math.min(result[j][i + j - 1] + 1, result[j + 1][i + j] + 1);
                if (s.charAt(j) == s.charAt(i + j)) {
                    result[j][i + j] = Math.min(result[j][i + j], result[j + 1][i + j - 1]);
                }
            }
        }

        return result[0][s.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println(ShortestPalindromeAfterInsertion.calculate("fcdabacef"));

    }

    @Test
    public void testWhenInsertionNeeded() {
        Assert.assertEquals(1, calculate("ab"));
        Assert.assertEquals(1, calculate("abab"));
        Assert.assertEquals(1, calculate("abbab"));
        Assert.assertEquals(3, calculate("abcd"));
        Assert.assertEquals(2, calculate("dabcd"));
    }

    @Test
    public void testWhenNoInsertionNeeded() {
        Assert.assertEquals(0, calculate("a"));
        Assert.assertEquals(0, calculate("aba"));
        Assert.assertEquals(0, calculate("abba"));
    }
}
