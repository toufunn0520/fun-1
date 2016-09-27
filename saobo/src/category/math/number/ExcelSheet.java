package category.math.number;

public class ExcelSheet {

    /**
     * [Leetcode 168] https://leetcode.com/problems/excel-sheet-column-title/
     *
     * <pre>
     * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
     * 
     * For example:
     * 
     *     1 -> A
     *     2 -> B
     *     3 -> C
     *     ...
     *     26 -> Z
     *     27 -> AA
     *     28 -> AB
     * </pre>
     *
     * @param n
     * @return
     */
    public String convertToTitle(int n) {
        return n == 0 ? "" : convertToTitle(--n / 26) + (char) ('A' + (n % 26));
    }

    /**
     * [Leetcode 171] https://leetcode.com/problems/excel-sheet-column-number/
     * 
     * <pre>
     * Given a column title as appear in an Excel sheet, return its corresponding column number.
     *
     * For example:
     *
     *     A -> 1
     *     B -> 2
     *     C -> 3
     *     ...
     *     Z -> 26
     *     AA -> 27
     *     AB -> 28
     * </pre>
     *
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Input is not valid!");
        }

        int result = 0;
        int i = s.length() - 1;
        int exponent = 0;
        while (i >= 0) {
            char curr = s.charAt(i);
            result = result + (int) Math.pow(26, exponent) * (curr - 'A' + 1);
            exponent++;
            i--;
        }

        return result;
    }
}
