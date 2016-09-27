package category.implementation.string.operations;

public class ReverseWordsInAString {

    private void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * [Leetcode 186] https://leetcode.com/problems/reverse-words-in-a-string-ii/
     * 
     * <pre>
     * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
     * The input string does not contain leading or trailing spaces and the words are always separated by a single space.
     *
     * For example,
     * Given s = "the sky is blue",
     * return "blue is sky the".
     *
     * Could you do it in-place without allocating extra space?
     * </pre>
     *
     * @param s
     */
    public void reverseWords(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }

        reverse(s, 0, s.length - 1);

        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                continue;
            }

            int start = i;
            while (i < s.length && s[i] != ' ') {
                i++;
            }

            int end = i == s.length - 1 ? i : i - 1;
            reverse(s, start, end);
        }

    }

    /**
     * [Leetcode 151] https://leetcode.com/problems/reverse-words-in-a-string/
     *
     * <pre>
     * Given an input string, reverse the string word by word.
     *
     * For example,
     * Given s = "the sky is blue",
     * return "blue is sky the".
     * </pre>
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder(s);
        s = sb.reverse().toString();

        sb.delete(0, s.length());
        for (int i = 0; i < s.length(); i++) {
            StringBuilder wordBuilder = new StringBuilder();
            while (i < s.length() && s.charAt(i) != ' ') {
                wordBuilder.append(s.charAt(i++));
            }

            if (wordBuilder.length() != 0 && sb.length() != 0) {
                sb.append(" ");
            }

            sb.append(wordBuilder.reverse());
        }

        return sb.toString();
    }
}
