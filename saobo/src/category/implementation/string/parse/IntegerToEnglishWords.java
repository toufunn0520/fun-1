package category.implementation.string.parse;

public class IntegerToEnglishWords {

    private static String[] HUNDREDS = { "Hundred", "Thousand", "Million", "Billion" };

    private static String[] TENS = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
    "Ninety" };

    private static String[] WITHIN_TWENTY = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
        "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
    "Nineteen" };

    private static String ZERO = "zero";

    public static void main(String[] args) {
        System.out.println(new IntegerToEnglishWords().numberToWords(100021022));
    }

    private String convertWithinThousand(int num) {
        int a = num / 100, b = num % 100, c = num % 10;
        String result = b < 20 ? WITHIN_TWENTY[b] : TENS[b / 10] + (c != 0 ? " " + WITHIN_TWENTY[c] : "");

        if (a > 0) {
            result = WITHIN_TWENTY[a] + " " + HUNDREDS[0] + (b != 0 ? " " + result : "");
        }

        return result;
    }

    /**
     * [Leetcode 273] https://leetcode.com/problems/integer-to-english-words/
     *
     * <pre>
     * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.
     * For example,
     * 123 -> "One Hundred Twenty Three"
     * 12345 -> "Twelve Thousand Three Hundred Forty Five"
     * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
     * </pre>
     *
     * @param num
     * @return
     */
    public String numberToWords(int num) {
        if (num == 0) {
            return ZERO;
        }

        String result = convertWithinThousand(num % 1000);
        for (int i = 0; i < 3; i++) {
            num /= 1000;
            result = num % 1000 != 0 ? convertWithinThousand(num % 1000) + " " + HUNDREDS[i + 1] + " " + result
                    : result;
        }

        return result.trim();
    }
}
