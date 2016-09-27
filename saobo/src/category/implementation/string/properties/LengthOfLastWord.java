package category.implementation.string.properties;

public class LengthOfLastWord {

    /**
     * [Leetcode 58] https://leetcode.com/problems/length-of-last-word/
     *
     * <pre>
     * Given a string s consists of upper/lower-case alphabets and empty space
     * characters ' ', return the length of last word in the string. If the last
     * word does not exist, return 0. Note: A word is defined as a character
     * sequence consists of non-space characters only. For example, Given s =
     * "Hello World", return 5.
     * </pre>
     */
    public static int lengthOfLastWord(String s) {
        int curCount = 0;
        int stringIndex = s.length() - 1;
        while (stringIndex > 0 && s.charAt(stringIndex) == ' ') {
            stringIndex--;
        }
        for (; stringIndex >= 0; stringIndex--) {
            if (s.charAt(stringIndex) != ' ') {
                curCount++;
            } else {
                return curCount;
            }
        }
        return curCount;
    }

}
