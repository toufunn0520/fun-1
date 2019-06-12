//facebook
/*
20. Valid Parentheses
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.
*/
class Solution {
    public boolean isValid(String s) {
        Stack<Character> res = new Stack();

        char[] chars = s.toCharArray();
        for (char c: chars) {
            switch (c) {
                case '}':
                    if (res.empty()) return false;
                    if (res.peek() == '{') {
                        res.pop();
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if (res.empty()) return false;
                    if (res.peek() == '[') {
                        res.pop();
                    } else {
                        return false;
                    }
                    break;
                case ')':
                    if (res.empty()) return false;
                    if (res.peek() == '(') {
                        res.pop();
                    } else {
                        return false;
                    }
                    break;
                default:
                    if (c == '(' || c == '[' || c == '{') {
                        res.push(c);
                    } else {
                        return false;
                    }
                    break;
            }
        }
        
        return res.empty();
    }
}
