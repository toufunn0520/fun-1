package category.implementation.string.parse;

import java.util.Stack;

public class SimplifyPath {

    public static void main(String[] args) {

        System.out.println(new SimplifyPath().simplifyPath("/...../.../abc "));
    }

    /**
     * [Leetcode 71] https://leetcode.com/problems/simplify-path/
     *
     * <pre>
     * Given an absolute path for a file (Unix-style), simplify it.
     * 
     * For example,
     * path = "/home/", => "/home"
     * path = "/a/./b/../../c/", => "/c"
     * </pre>
     * 
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        StringBuilder result = new StringBuilder();

        if (path == null || path.length() == 0) {
            return result.toString();
        }

        String[] strs = path.split("/");

        Stack<String> stack = new Stack<String>();

        for (String s : strs) {

            if (s.length() == 0 || s.equals(".")) {

            } else if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(s);
            }

        }

        if (stack.isEmpty()) {
            result.append('/');

        } else {

            while (!stack.isEmpty()) {
                result.insert(0, stack.pop());
                result.insert(0, '/');
            }
        }

        return result.toString();
    }
}
