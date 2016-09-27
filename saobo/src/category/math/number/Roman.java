package category.math.number;

import java.util.HashMap;
import java.util.Map;

public class Roman {

    public static void main(String[] args) {
        System.out.println(new Roman().romanToInt("MCMXCVI"));

    }

    /**
     * [Leetcode 12] https://leetcode.com/problems/integer-to-roman/
     *
     * <pre>
     * Given an integer, convert it to a roman numeral.
     * Input is guaranteed to be within the range from 1 to 3999.
     * </pre>
     *
     * @param num
     * @return
     */
    public String intToRaman(int num) {
        if (num <= 0) {
            return null;
        }

        int[] numbers = { 1000, 900, 500, 100, 90, 50, 10, 9, 5, 4, 1 };
        String[] romans = { "M", "CM", "D", "C", "IC", "L", "X", "IX", "V", "IV", "I" };

        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            for (int i = 0; i < numbers.length;) {
                if (num - numbers[i] >= 0) {
                    num -= numbers[i];
                    sb.append(romans[i]);
                } else {
                    i++;
                }
            }
        }

        return sb.toString();
    }

    /**
     * [Leetcode 13] https://leetcode.com/problems/roman-to-integer/
     * 
     * <pre>
     * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
     * </pre>
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> romanToInt = new HashMap<Character, Integer>();
        romanToInt.put('M', 1000);
        romanToInt.put('D', 500);
        romanToInt.put('C', 100);
        romanToInt.put('L', 50);
        romanToInt.put('X', 10);
        romanToInt.put('V', 5);
        romanToInt.put('I', 1);

        int previous = Integer.MIN_VALUE;
        int result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char current = s.charAt(i);
            int currentInt = romanToInt.get(current);
            if (currentInt >= previous) {
                result += currentInt;
            } else {
                result -= currentInt;
            }
            previous = currentInt;
        }

        return result;
    }

}
