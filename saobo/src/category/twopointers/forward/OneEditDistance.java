package category.twopointers.forward;

public class OneEditDistance {

    /**
     * [Leetcode 161] https://leetcode.com/problems/one-edit-distance/
     * 
     * <pre>
     * Given two strings S and T, determine if they are both one edit distance apart.
     * </pre>
     * 
     * @param s
     * @param t
     * @return
     */
    public static boolean isOneEditDistance(String s, String t) {
        if (s.length() > t.length()) {
            return isOneEditDistance(t, s);
        }

        if (t.length() - s.length() > 1) {
            return false;
        }

        boolean missedMatchBefore = false;
        for (int i = 0, j = 0; i < s.length(); ++i, ++j) {
            if (s.charAt(i) != t.charAt(j)) {
                if (missedMatchBefore) {
                    return false;
                }
                missedMatchBefore = true;
                if (s.length() < t.length()) {
                    --i;
                }
            }
        }
        return missedMatchBefore || (s.length() < t.length());
    }

    public static void main(String[] args) {
        System.out.println(isOneEditDistance("a", ""));
    }
}
