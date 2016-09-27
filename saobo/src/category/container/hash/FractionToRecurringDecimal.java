package category.container.hash;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {

    public static void main(String[] args) {
        System.out.println(new FractionToRecurringDecimal().fractionToDecimal(-1, Integer.MIN_VALUE));
    }

    /**
     * [Leetcode 166] https://leetcode.com/problems/fraction-to-recurring-decimal/
     * 
     * <pre>
     * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
     * 
     * If the fractional part is repeating, enclose the repeating part in parentheses.
     * 
     * For example,
     * 
     * Given numerator = 1, denominator = 2, return "0.5".
     * Given numerator = 2, denominator = 1, return "2".
     * Given numerator = 2, denominator = 3, return "0.(6)".
     * </pre>
     *
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
            result.append("-");
        }

        long absoludeNumberator = Math.abs(new Long(numerator));
        long absoludeDenominator = Math.abs(new Long(denominator));

        long integer = absoludeNumberator / absoludeDenominator;

        result.append(integer);

        if (absoludeNumberator % absoludeDenominator == 0) {
            return result.toString();
        }

        result.append(".");

        StringBuilder decimals = new StringBuilder();
        Map<Long, Integer> remainderToIndex = new HashMap<Long, Integer>();
        long remainder = absoludeNumberator % absoludeDenominator;

        int count = 0;
        while (remainder != 0) {
            if (remainderToIndex.containsKey(remainder)) {
                decimals.insert(remainderToIndex.get(remainder), "(");
                decimals.append(")");
                break;
            }

            remainderToIndex.put(remainder, count++);
            decimals.append(remainder * 10 / absoludeDenominator);
            remainder = remainder * 10 % absoludeDenominator;
        }

        return result.append(decimals.toString()).toString();
    }

}
