package category.search.backtracking;

import interview.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class StringWithWildCards {

    public static void main(String[] args) {
        List<String> result = new StringWithWildCards().getPossibleStrings("a*b*c");

        ListUtils.listPrint(result);

    }

    /**
     * [Google phone] Given a string, replace '*' in the string with either j or k.
     * 
     * @param s
     * @return All possible strings.
     */
    public List<String> getPossibleStrings(String s) {
        List<String> result = new ArrayList<String>();

        if (s == null || s.isEmpty()) {
            return result;
        }

        replaceWildCardsHelper(new StringBuilder(s), 0, result);

        return result;
    }

    public void replaceWildCardsHelper(StringBuilder sb, int start, List<String> result) {
        if (start == sb.length()) {
            result.add(sb.toString());
            return;
        }

        if (sb.charAt(start) == '*') {
            sb.setCharAt(start, 'j');
            replaceWildCardsHelper(sb, start + 1, result);
            sb.setCharAt(start, 'k');
            replaceWildCardsHelper(sb, start + 1, result);
        } else {
            replaceWildCardsHelper(sb, start + 1, result);
        }

    }

}
