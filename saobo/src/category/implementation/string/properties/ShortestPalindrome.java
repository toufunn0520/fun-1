package category.implementation.string.properties;

public class ShortestPalindrome {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(new ShortestPalindrome().shortestPalindrome("bbba"));
    }

    /**
     * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and
     * return the shortest palindrome you can find by performing this transformation. For example, given "aacecaaa",
     * return "aaacecaaa"; given "abcd", return "dcbabcd".
     * 
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {
        if (s == null || s.length() <= 1)
            return s;

        String result = null;

        int len = s.length();
        int mid = len / 2;

        for (int i = mid; i >= 1; i--) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                if ((result = scanFromCenter(s, i - 1, i)) != null)
                    return result;
            } else {
                if ((result = scanFromCenter(s, i - 1, i - 1)) != null)
                    return result;
            }
        }

        return result;
    }

    private String scanFromCenter(String s, int l, int r) {
        int i = 1;

        // scan from center to both sides
        for (; l - i >= 0; i++) {
            if (s.charAt(l - i) != s.charAt(r + i))
                break;
        }

        // if not end at the beginning of s, return null
        if (l - i >= 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder(s.substring(r + i));
        sb.reverse();
        // System.out.println("return  " + sb.append(s).toString());
        return sb.append(s).toString();
    }
}
