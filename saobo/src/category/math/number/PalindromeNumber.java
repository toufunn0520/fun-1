package category.math.number;

public class PalindromeNumber {

    /**
     * [Leetcode 9] https://leetcode.com/problems/palindrome-number/
     *
     * <pre>
     * Determine whether an integer is a palindrome. Do
     * this without extra space.
     * </pre>
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        String s = String.valueOf(x);
        if (s.length() <= 1) {
            return true;
        }

        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
