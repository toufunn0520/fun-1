package category.math.number;

public class ReverseInteger {

    /**
     * [Leetcode 7] https://leetcode.com/problems/reverse-integer/
     * 
     * <pre>
     * Reverse digits of an integer.
     *
     * Example1: x = 123, return 321
     * Example2: x = -123, return -321
     * </pre>
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        StringBuilder sb = new StringBuilder();

        String s = String.valueOf(Math.abs(x));

        for (int i = s.length() - 1; i >= 0; i--) {
            if (sb.length() == 0 && s.charAt(i) == '0') {
                continue;
            }

            sb.append("" + s.charAt(i));
        }

        if (x < 0) {
            sb.insert(0, "-");
        }

        int result;
        try {
            result = Integer.valueOf(sb.toString());
        } catch (NumberFormatException e) {
            result = 0;
        }
        return result;
    }
}
