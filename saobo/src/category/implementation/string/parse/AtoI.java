package category.implementation.string.parse;

public class AtoI {

    /**
     * [Leetcode 8] https://leetcode.com/problems/string-to-integer-atoi/
     *
     * <pre>
     * Implement atoi to convert a string to an integer. Hint: Carefully consider all possible input cases. If you want
     * a challenge, please do not see below and ask yourself what are the possible input cases. Notes: It is intended
     * for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input
     * requirements up front.
     * </pre>
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int sign = 1;
        int i = 0;

        while (str.charAt(i) == ' ') {
            i++;
        }

        if (str.charAt(i) == '+') {
            i++;
        } else if (str.charAt(i) == '-') {
            i++;
            sign = -1;
        }

        double retValue = 0;
        for (; i < str.length(); i++) {
            int digit = str.charAt(i) - '0';
            if (digit > 9 || digit < 0) {
                break;
            }

            retValue = retValue * 10 + digit;
        }

        retValue = sign * retValue;

        if (retValue > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (retValue < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int) retValue;
        }
    }
}
