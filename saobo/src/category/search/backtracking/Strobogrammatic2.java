package category.search.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Strobogrammatic2 {

    /**
     * [Leetcode 247] https://leetcode.com/problems/strobogrammatic-number-ii/
     * 
     * <pre>
     * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
     *
     * Find all strobogrammatic numbers that are of length = n.
     *
     * For example,
     * Given n = 2, return ["11","69","88","96"].
     * </pre>
     *
     * @param n
     * @return
     */
    public static List<String> findStrobogrammatic(int n) {
        if (n < 0) {
            return null;
        }

        return getAll(n, n);
    }

    private static List<String> getAll(int restNumOfDigits, int totalNumOfDigits) {
        List<String> current = new ArrayList<String>();
        if (restNumOfDigits == 0) {
            current.add("");
        } else if (restNumOfDigits == 1) {
            current.add("0");
            current.add("1");
            current.add("8");
        } else {
            List<String> subStrings = getAll(restNumOfDigits - 2, totalNumOfDigits);
            for (String subString : subStrings) {
                current.add("1" + subString + "1");
                current.add("8" + subString + "8");
                current.add("6" + subString + "9");
                current.add("9" + subString + "6");

                if (restNumOfDigits < totalNumOfDigits) {
                    current.add("0" + subString + "0");
                }
            }
        }

        return current;
    }

    public static void main(String[] args) {
        System.out.println(findStrobogrammatic(3));

    }

    String low = "", high = "";

    private Map<Character, Character> map = new HashMap<>();
    {
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
    }

    private boolean compare(String a, String b) {
        if (a.length() != b.length())
            return a.length() < b.length();
        int i = 0;
        while (i < a.length() && a.charAt(i) == b.charAt(i))
            i++;
        return i == a.length() ? true : a.charAt(i) <= b.charAt(i);
    }

    private void strobogrammaticInRange(char[] current, int[] count, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) {
            String s = new String(current);
            if ((current[0] != '0' || current.length == 1) && compare(low, s) && compare(s, high)) {
                count[0]++;
            }
            return;
        }
        for (Character c : map.keySet()) {
            current[lowIndex] = c;
            current[highIndex] = map.get(c);
            if ((lowIndex == highIndex && c == map.get(c)) || lowIndex < highIndex)
                strobogrammaticInRange(current, count, lowIndex + 1, highIndex - 1);
        }
    }

    /**
     * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down). Write
     * a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high. For example,
     * Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers. Note: Because
     * the range might be a large number, the low and high numbers are represented as string.
     *
     * @param low
     * @param high
     * @return
     */
    public int strobogrammaticInRange(String low, String high) {

        this.low = low;
        this.high = high;
        int result = 0;
        for (int n = low.length(); n <= high.length(); n++) {
            int[] count = new int[1];
            strobogrammaticInRange(new char[n], count, 0, n - 1);
            result += count[0];
        }
        return result;
    }

}
