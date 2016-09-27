package category.famousalgorithm;

public class ShortestPalindrome {

    public static void main(String[] args) {
        System.out.println(shortestPalindrome("aacecaaa"));

    }

    /**
     * [Leetcode 214] https://leetcode.com/problems/shortest-palindrome/
     * 
     * <pre>
     * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
     * Find and return the shortest palindrome you can find by performing this transformation.
     *
     * For example:
     *
     * Given "aacecaaa", return "aaacecaaa".
     * Given "abcd", return "dcbabcd".
     * </pre>
     *
     * @param s
     * @return
     */
    public static String shortestPalindrome(String original) {
        if (original == null) {
            return "";
        }

        String reversed = new StringBuilder(original).reverse().toString();
        String compound = original + "#" + reversed;
        // longestPrefixMatch[i]: longest match end by char i.
        int[] longestPrefixMatch = new int[compound.length()];

        for (int i = 1; i < compound.length(); i++) {
            int j = longestPrefixMatch[i - 1];
            while (j > 0 && compound.charAt(i) != compound.charAt(j)) {
                j = longestPrefixMatch[j - 1];
            }
            if (compound.charAt(i) == compound.charAt(j)) {
                j += 1;
                longestPrefixMatch[i] = j;
            }
        }

        return reversed.substring(0, reversed.length() - longestPrefixMatch[compound.length() - 1]) + original;
    }

}
