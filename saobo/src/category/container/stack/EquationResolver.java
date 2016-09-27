package category.container.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * [Facebook onsite]: 求解一元一次方程。
 */
public class EquationResolver {

    public final static String BRACES = "()";

    public final static String OPERATORS = "+-*/";

    public static void main(String[] args) {
        EquationResolver equation = new EquationResolver();
        double x = equation.evaluate("3+2*3+5x-(3+5*3)=9-7x+2*3");
        System.out.println(x);
    }

    public double evaluate(String expression) {
        String[] exp = expression.replace(" ", "") // 表达式可能有空格
                .replace("x", "*x") // 把5x替换成5*x的标准表达式
                .split("="); // 把方程分解成左右两个表达式
        double f0 = parse(exp[0], 0);
        double f1 = parse(exp[0], 1);
        double g0 = parse(exp[1], 0);
        double g1 = parse(exp[1], 1);
        return (f0 - g0) / ((f0 - g0) - (f1 - g1));
    }

    // 这个方法就是leetcode上很常规的求逆波兰达式的值
    public int evaluateRPN(Queue<String> rpn) {
        Stack<Integer> stack = new Stack<>(); // 保存运算结果
        while (!rpn.isEmpty()) {
            String token = rpn.poll();
            if (OPERATORS.contains(token)) {
                int b = stack.pop();
                int a = stack.pop();
                if (token.equals("+")) {
                    stack.push(a + b);
                } else if (token.equals("-")) {
                    stack.push(a - b);
                } else if (token.equals("*")) {
                    stack.push(a * b);
                } else if (token.equals("/")) {
                    stack.push(a / b);
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    // +-*/的运算符优先度，括号为0
    public int getPrecedence(String op) {
        if (op.equals("+") || op.equals("-")) {
            return 1;
        } else if (op.equals("(")) { // 注意，把(的优先度降为最低,因为这个方法只用来比较+-*/运算符的优先度，其他一概为0
            return 0;
        }
        return 2; // *和/的优先度相同，都为2
    }

    // 求单边表达式的值
    public int parse(String expression, int x) {
        Queue<String> rpn = new LinkedList<>(); // 保存逆波兰表达式
        Stack<String> opstack = new Stack<>(); // 保存运算符
        expression = expression.replace("x", "" + x).replace("(-", "(0-"); // 注意左括号后面可能紧跟负数的情况
        // 把所有运算符和左右括号当成分隔符，true表示把所有分隔符也一同输出出去
        StringTokenizer tokenizer = new StringTokenizer(expression, OPERATORS + BRACES, true);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (OPERATORS.contains(token)) { // it is an operator
                while (!opstack.isEmpty() && getPrecedence(opstack.peek()) >= getPrecedence(token)) {
                    rpn.offer(opstack.pop());
                }
                opstack.push(token);
            } else if (BRACES.contains(token)) {
                if (token.equals("(")) { // left brace
                    opstack.push(token);
                } else { // right brace
                    while (!opstack.isEmpty() && !"(".equals(opstack.peek())) {
                        rpn.offer(opstack.pop());
                    }
                    opstack.pop();
                }
            } else { // it is a number
                rpn.offer(token);
            }
        }
        // 注意，当我们遍历完表达式的时候，别忘了运算符stack里面还有东西呢。
        // 一定要把它们全部pop到rpn队列里面去。
        while (!opstack.isEmpty()) {
            rpn.offer(opstack.pop());
        }
        return evaluateRPN(rpn);
    }
}
