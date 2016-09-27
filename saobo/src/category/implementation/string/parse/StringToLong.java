package category.implementation.string.parse;

import java.math.BigDecimal;

public class StringToLong {

    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE + 1);
        System.out.println(String.valueOf(Long.MIN_VALUE).length());
        System.out.println(String.valueOf(Long.MIN_VALUE));
        System.out.println(String.valueOf(Long.MAX_VALUE));
        System.out.println(stringtolong("-00000000000000000000009223372036854775808"));
        System.out.println(stringtolong("00000000000000000000009223372036854775807"));
        System.out.println(stringtolong("00000000000000000000009223372036854775808"));
    }

    public static long stringtolong(String str) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("invalid input");
        }

        BigDecimal[] digits = new BigDecimal[11];

        for (int i = 0; i < 11; i++) {
            digits[i] = new BigDecimal(i);
        }

        int length = str.length();
        BigDecimal result = BigDecimal.ZERO;
        int sign = 1;

        // parse sign
        int i = 0;
        if (str.length() > 1) {
            if (str.charAt(i) == '+') {
                i++;
            } else if (str.charAt(i) == '-') {
                i++;
                sign = -1;
            }
        }

        while (str.charAt(i) == '0') {
            i++;
        }

        if (str.length() - i > 20) {
            throw new RuntimeException("The input length is too long");
        }

        // parse numbers
        while (i < length) {
            if ((str.charAt(i)) >= '0' && (str.charAt(i)) <= '9') {
                result = result.multiply(digits[10]).add(digits[str.charAt(i) - '0']);
                i++;
            } else {
                throw new RuntimeException("Invalid input");
            }
        }

        result = result.multiply(new BigDecimal(sign));

        BigDecimal max = new BigDecimal(Long.MAX_VALUE);
        BigDecimal min = new BigDecimal(Long.MIN_VALUE);

        if (result.compareTo(min) < 0 || result.compareTo(max) > 0) {
            throw new RuntimeException("Result exceeds range of long");
        }

        return result.longValue();

    }
}
