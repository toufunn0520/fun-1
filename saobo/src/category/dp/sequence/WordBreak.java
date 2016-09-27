package category.dp.sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    private static void dfs(String s, boolean[][] seg, int start, int length, int depth, List<String> result,
            StringBuffer sb, Set<String> dict) {
        if (depth == length) {
            String t = sb.toString();
            result.add(t.substring(0, t.length() - 1));
            return;
        }

        for (int len = 1; len <= length; len++) {
            if (seg[start][len]) {
                String t = s.substring(start, start + len);
                if (!dict.contains(t)) {
                    continue;
                }
                int beforeAddLen = sb.length();
                sb.append(t).append(" ");
                dfs(s, seg, start + len, length, start + len, result, sb, dict);
                sb.delete(beforeAddLen, sb.length());
            }
        }
    }

    public static void main(String[] args) {
        String a = "catsanddog";
        Set<String> dict = new HashSet<String>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
        List<String> result = new WordBreak().wordBreak2(a, dict);
        for (String s : result) {
            System.out.print(s + ", ");
        }
    }

    /**
     * [Leetcode 140] https://leetcode.com/problems/word-break-ii/
     *
     * <pre>
     * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
     * 
     * Return all such possible sentences.
     * 
     * For example, given
     * s = "catsanddog",
     * dict = ["cat", "cats", "and", "sand", "dog"].
     * 
     * A solution is ["cats and dog", "cat sand dog"].
     *
     * </pre>
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static List<String> wordBreak2(String s, Set<String> dict) {
        List<String> result = new ArrayList<String>();
        if (s == null || dict.size() == 0) {
            return result;
        }
        int length = s.length();
        // seg(i, j) means substring t start from i and length is j can be
        // segmented into dictionary words
        boolean[][] seg = new boolean[length][length + 1];
        for (int len = 1; len <= length; len++) {
            for (int i = 0; i < length - len + 1; i++) {
                String t = s.substring(i, i + len);
                if (dict.contains(t)) {
                    seg[i][len] = true;
                    continue;
                }
                for (int k = 1; k < len; k++) {
                    if (seg[i][k] && seg[i + k][len - k]) {
                        seg[i][len] = true;
                        break;
                    }
                }
            }
        }
        if (!seg[0][length]) {
            return result;
        }

        int depth = 0;
        dfs(s, seg, 0, length, depth, result, new StringBuffer(), dict);

        return result;
    }

    /**
     * [Leetcode 139] https://leetcode.com/problems/word-break/
     *
     * <pre>
     * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
     * 
     * For example, given
     * s = "leetcode",
     * dict = ["leet", "code"].
     * 
     * Return true because "leetcode" can be segmented as "leet code".
     * </pre>
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        int len = s.length();

        boolean[] breakString = new boolean[len + 1];

        Arrays.fill(breakString, false);
        breakString[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (breakString[j] && wordDict.contains(s.substring(j, i))) {
                    breakString[i] = true;
                    continue;
                }
            }
        }

        return breakString[len];
    }
}
