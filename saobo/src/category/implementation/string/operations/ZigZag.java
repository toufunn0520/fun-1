package category.implementation.string.operations;

public class ZigZag {

    public static void main(String[] args) {
        System.out.println(new ZigZag().convert("PAYPALISHIRING", 3));
    }

    /**
     * [Leetcode 6] https://leetcode.com/problems/zigzag-conversion/
     * 
     * <pre>
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
     * (you may want to display this pattern in a fixed font for better legibility)
     *
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * And then read line by line: "PAHNAPLSIIGYIR"
     * Write the code that will take a string and make this conversion given a number of rows:
     *
     * string convert(string text, int nRows);
     * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
     * </pre>
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        StringBuilder[] sbs = new StringBuilder[numRows];

        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }

        int row = 0;
        int index = 0;
        while (index < s.length()) {
            for (row = 0; row < numRows && index < s.length(); row++) {
                sbs[row].append(s.charAt(index++));
            }

            for (row = numRows - 2; row > 0 && index < s.length(); row--) {
                sbs[row].append(s.charAt(index++));
            }
        }

        for (int i = 1; i < numRows; i++) {
            sbs[0].append(sbs[i]);
        }

        return sbs[0].toString();
    }
}
