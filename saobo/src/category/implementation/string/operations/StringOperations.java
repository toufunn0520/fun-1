package category.implementation.string.operations;

import org.junit.Assert;
import org.junit.Test;

public class StringOperations {

    /**
     * Assume input strings are digits only
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String add(String s1, String s2) {
        if (s1 == null || s1.length() == 0) {
            return s2;
        }

        if (s2 == null || s2.length() == 0) {
            return s1;
        }

        StringBuilder reversedSum = new StringBuilder();

        int carry = 0;
        int i = s1.length() - 1;
        int j = s2.length() - 1;

        while (i >= 0 || j >= 0) {
            if (i >= 0) {
                carry += s1.charAt(i--) - '0';
            }

            if (j >= 0) {
                carry += s2.charAt(j--) - '0';
            }

            reversedSum.append(carry % 10);

            carry /= 10;
        }

        if (carry > 0) {
            reversedSum.append(carry);
        }

        return reversedSum.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("100", "1"));

    }

    /**
     * Assume input strings are digits only.
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String minus(String s1, String s2) {
        if (s1 == null || s2.length() == 0) {
            return "-" + s2;
        }

        if (s2 == null || s2.length() == 0) {
            return s1;
        }

        if (s2.length() > s1.length() || (s1.length() == s2.length() && s1.charAt(0) < s2.charAt(0))) {
            return "-" + minus(s2, s1);
        }

        StringBuilder reversedDifference = new StringBuilder();
        int i = s1.length() - 1;
        int j = s2.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0) {

            if (i >= 0) {
                carry += s1.charAt(i--) - '0';
            }

            if (j >= 0) {
                carry -= s2.charAt(j--) - '0';
            }

            if (carry < 0) {
                reversedDifference.append(10 + carry);
                carry = -1;
            } else {
                reversedDifference.append(carry);
                carry = 0;
            }
        }

        i = reversedDifference.length() - 1;
        while (i > 0 && reversedDifference.charAt(i) == '0') {
            i--;
        }
        i = reversedDifference.length() - i - 1;

        return reversedDifference.reverse().substring(i).toString();
    }

    /**
     * [Leetcode 43] https://leetcode.com/problems/multiply-strings/
     *
     * <pre>
     * Given two numbers represented as strings, return multiplication of the numbers as a string.
     * Note: The numbers can be arbitrarily large and are non-negative.
     * </pre>
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String multiply(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return "0";
        }

        int[] result = new int[s1.length() + s2.length()];

        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                result[s1.length() - i - 1 + s2.length() - j - 1] += (s1.charAt(i) - '0') * (s2.charAt(j) - '0');
            }
        }

        int carry = 0;
        for (int i = 0; i < result.length; i++) {
            carry += result[i];
            result[i] = carry % 10;
            carry /= 10;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = result.length - 1; i > 0; i--) {
            if (sb.length() == 0 && result[i] == 0) {
                continue;
            }

            sb.append(result[i]);
        }

        sb.append(result[0]);

        return sb.toString();
    }

    /**
     * [Leetcode 67] https://leetcode.com/problems/add-binary/
     *
     * <pre>
     * Given two binary strings, return their sum (also a binary string). For example, a = "11" b = "1" Return "100".
     * </pre>
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        a = a == null ? "" : a;
        b = b == null ? "" : b;

        String reversedA = new StringBuilder(a).reverse().toString();
        String reversedB = new StringBuilder(b).reverse().toString();
        StringBuilder result = new StringBuilder();

        while (reversedA.length() != reversedB.length()) {
            if (reversedA.length() > reversedB.length()) {
                reversedB = reversedB + "0";
            } else {
                reversedA = reversedA + "0";
            }
        }

        int carry = 0;
        int i = 0;
        for (; i < reversedA.length(); i++) {
            int valueA = reversedA.charAt(i) - '0';
            int valueB = reversedB.charAt(i) - '0';
            int value = valueA + valueB + carry;
            carry = value / 2;
            value %= 2;
            result.append(value);
        }

        if (carry == 1) {
            result.append(1);
        }

        return result.reverse().toString();
    }

    /**
     * [Leetcode 66] https://leetcode.com/problems/plus-one/
     *
     * <pre>
     * Given a non-negative number represented as an array of digits, plus one to the number. The digits are stored such
     * that the most significant digit is at the head of the list.
     * </pre>
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            int[] res = { 1 };
            return res;
        }

        int carry = 0;
        digits[digits.length - 1] += 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] += carry;
            if (digits[i] > 9) {
                digits[i] -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
        }

        if (carry == 1) {
            int[] res = new int[digits.length + 1];
            for (int i = 0; i < digits.length; i++) {
                res[i + 1] = digits[i];
            }
            res[0] = 1;
            return res;
        }

        return digits;
    }

    @Test
    public void testAdd() {
        Assert.assertEquals("1", add("1", "0"));
        Assert.assertEquals("101", add("100", "1"));
        Assert.assertEquals("0", add("0", "0"));
        Assert.assertEquals("1098", add("999", "99"));
        Assert.assertEquals("1001", add("1", "1000"));
    }

    @Test
    public void testMinus() {
        Assert.assertEquals("1", minus("1", "0"));
        Assert.assertEquals("99", minus("100", "1"));
        Assert.assertEquals("-99", minus("1", "100"));
        Assert.assertEquals("1", minus("100", "99"));
        Assert.assertEquals("-1", minus("99", "100"));
        Assert.assertEquals("0", minus("0", "0"));
        Assert.assertEquals("0", minus("1", "1"));
        Assert.assertEquals("1", minus("1", "0"));
    }

    @Test
    public void testMultiply() {
        Assert.assertEquals("0", multiply("999", "0"));
        Assert.assertEquals("100", multiply("100", "1"));
        Assert.assertEquals("98901", multiply("99", "999"));
        Assert.assertEquals("10000", multiply("100", "100"));
        Assert.assertEquals("10", multiply("5", "2"));
        Assert.assertEquals("0", multiply("0", "0"));
        Assert.assertEquals("1", multiply("1", "1"));
    }
}
