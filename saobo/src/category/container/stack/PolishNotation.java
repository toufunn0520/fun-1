package category.container.stack;

import java.util.Stack;

public class PolishNotation {

    private static final String[] OPERATORS = { "+", "-", "*", "/" };

    public static void main(String[] args) {
        String[] tokens = { "2", "1", "+", "3", "*" };

        System.out.println(new PolishNotation().evalRPN(tokens));

    }

    private int calculate(int num1, int num2, char operator) {
        switch (operator) {
        case '+':
            return num1 + num2;
        case '-':
            return num1 - num2;
        case '*':
            return num1 * num2;
        case '/':
            return num1 / num2;
        default:
            return 0;
        }
    }

    /**
     * [Leetcode 150] https://leetcode.com/problems/evaluate-reverse-polish-notation/
     * 
     * <pre>
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
     *
     * Some examples:
     *   ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
     *   ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     * </pre>
     *
     * Caution: the order to do calculation matters for /
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        Stack<Integer> toProcess = new Stack<Integer>();

        for (String token : tokens) {
            if (isOperator(token)) {
                int num1 = toProcess.pop();
                int num2 = toProcess.pop();

                int result = calculate(num2, num1, token.toCharArray()[0]);
                toProcess.push(result);
            } else {
                toProcess.push(Integer.parseInt(token));
            }
        }

        return toProcess.pop();
    }

    private boolean isOperator(String s) {
        for (String operator : OPERATORS) {
            if (s.equals(operator)) {
                return true;
            }
        }
        return false;
    }

}
