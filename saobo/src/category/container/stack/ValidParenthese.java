package category.container.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParenthese {

    public static void main(String[] args) {
        System.out.println(new ValidParenthese().isValid("[]"));
    }

    /**
     * [Leetcode 20] https://leetcode.com/problems/valid-parentheses/
     * 
     * <pre>
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     *
     * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     * </pre>
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {

        if (s == null || s.isEmpty()) {
            return true;
        }

        Map<Character, Character> parentheses = new HashMap<Character, Character>();
        parentheses.put(')', '(');
        parentheses.put(']', '[');
        parentheses.put('}', '{');

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (!parentheses.containsKey(current)) {
                stack.push(current);
            } else {
                if (stack.isEmpty() || parentheses.get(current) != stack.pop()) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
