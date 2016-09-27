package category.implementation.string.properties;

public class LongestCommonPrefix {

    /**
     * [Leetcode 14] https://leetcode.com/problems/longest-common-prefix/ Write a function to find the longest common
     * prefix string amongst an array of strings.
     * 
     * @param s
     * @return
     */
    public String longestCommonPrefix(String[] s) {
        if (s == null || s.length == 0)
            return "";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s[0].length(); i++) {
            char currentChar = s[0].charAt(i);

            for (int j = 1; j < s.length; j++) {
                if (i >= s[j].length() || s[j].charAt(i) != currentChar) {
                    return sb.toString();
                }
            }

            sb.append("" + currentChar);
        }

        return sb.toString();
    }
}
