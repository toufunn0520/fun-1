package category.container.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BasicCalculator {

    private final static String OPERATORS = "+-*/";

    public static void main(String[] args) {
        System.out.println(new BasicCalculator().calculate1("(1+(4+5+2)-3)+(6+8)  "));

    }

    private long calculate(long num1, long num2, char operator) {
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
            throw new RuntimeException();
        }
    }

    /**
     * [Leetcode 224] https://leetcode.com/problems/basic-calculator/
     *
     * <pre>
     * Implement a basic calculator to evaluate a simple expression string.
     * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers
     * and empty spaces .
     * 
     * You may assume that the given expression is always valid.
     * 
     * Some examples:
     * "1 + 1" = 2
     * " 2-1 + 2 " = 3
     * "(1+(4+5+2)-3)+(6+8)" = 23
     * </pre>
     *
     * @param s
     * @return
     */
    public int calculate1(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(1);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                int j = i + 1;
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    num = 10 * num + (s.charAt(j) - '0');
                    j++;
                }
                res += stack.pop() * num;
                i = j - 1;
            } else if (c == '+' || c == '(') {
                stack.push(stack.peek());
            } else if (c == '-') {
                stack.push(-1 * stack.peek());
            } else if (c == ')') {
                stack.pop();
            }
        }
        return res;
    }

    /**
     * [Leetcode 227] https://leetcode.com/problems/basic-calculator-ii/
     *
     * <pre>
     * Implement a basic calculator to evaluate a simple expression string.
     * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
     * The integer division should truncate toward zero.
     * You may assume that the given expression is always valid.
     * 
     * Some examples:
     * "3+2*2" = 7
     * " 3/2 " = 1
     * " 3+5 / 2 " = 5
     * </pre>
     *
     * @param s
     * @return
     */
    public int calculate2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        s = s.replaceAll(" ", "");
        Queue<String> rpn = getReversePolishExpression(s);
        Stack<Long> numbers = new Stack<Long>();

        while (!rpn.isEmpty()) {
            String current = rpn.poll();

            if (!OPERATORS.contains(current)) {
                numbers.push(Long.parseLong(current));
            } else {
                long num2 = numbers.pop();
                long num1 = numbers.pop();

                long result = calculate(num1, num2, current.toCharArray()[0]);
                numbers.push(result);
            }
        }

        long result = numbers.pop();
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int) result;
        }
    }

    private int getPriority(String operator) {
        if (operator.equals("*") || operator.equals("/")) {
            return 1;
        } else if (operator.equals("+") || operator.equals("-")) {
            return 0;
        } else {
            return -1;
        }
    }

    private Queue<String> getReversePolishExpression(String expression) {
        StringTokenizer tokenizer = new StringTokenizer(expression, OPERATORS, true);
        Stack<String> operators = new Stack<String>();
        Queue<String> rpn = new LinkedList<String>();

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (OPERATORS.contains(token)) {
                while (!operators.isEmpty() && getPriority(operators.peek()) >= getPriority(token)) {
                    rpn.offer(operators.pop());
                }

                operators.push(token);
            } else {
                rpn.offer(token);
            }
        }

        while (!operators.isEmpty()) {
            rpn.offer(operators.pop());
        }

        return rpn;
    }

}
