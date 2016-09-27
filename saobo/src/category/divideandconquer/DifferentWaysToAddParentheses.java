package category.divideandconquer;

import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParentheses {

    private int calculate(int num1, int num2, Character operation) {
        switch (operation) {
        case '*':
            return num1 * num2;
        case '+':
            return num1 + num2;
        case '-':
            return num1 - num2;
        default:
            throw new RuntimeException("Invalid operation detected " + operation);
        }

    }

    private List<Integer> dfsCalculation(List<Integer> numbers, List<Character> operators) {
        List<Integer> results = new ArrayList<>();
        if (numbers.size() == 1) {
            results.add(numbers.get(0));
            return results;
        }

        for (int i = 0; i < operators.size(); i++) {
            char c = operators.get(i);
            List<Integer> leftRes = dfsCalculation(numbers.subList(0, i + 1), operators.subList(0, i));
            List<Integer> rightRes = dfsCalculation(numbers.subList(i + 1, numbers.size()),
                    operators.subList(i + 1, operators.size()));
            for (int left : leftRes) {
                for (int right : rightRes) {
                    results.add(calculate(left, right, c));
                }
            }
        }
        return results;
    }

    /**
     * [Leetcode 241] https://leetcode.com/problems/different-ways-to-add-parentheses/
     * 
     * <pre>
     * Given a string of numbers and operators, return all possible results from computing all the different possible ways to
     * group numbers and operators. The valid operators are +, - and *.
     * 
     * Example 1
     * Input: "2-1-1".
     * 
     * ((2-1)-1) = 0
     * (2-(1-1)) = 2
     * Output: [0, 2]
     * 
     * 
     * Example 2
     * Input: "2*3-4*5"
     * 
     * (2*(3-(4*5))) = -34
     * ((2*3)-(4*5)) = -14
     * ((2*(3-4))*5) = -10
     * (2*((3-4)*5)) = -10
     * (((2*3)-4)*5) = 10
     * Output: [-34, -14, -10, -10, 10]
     * </pre>
     *
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<Integer>();

        if (input == null) {
            return result;
        }

        List<Integer> nums = new ArrayList<Integer>();
        List<Character> operators = new ArrayList<Character>();
        for (int i = 0; i < input.length();) {
            int num = 0;
            while (i < input.length() && input.charAt(i) <= '9' && input.charAt(i) >= '0') {
                num = num * 10 + input.charAt(i++) - '0';
            }
            nums.add(num);

            if (i < input.length()) {
                operators.add(input.charAt(i++));
            }
        }

        return dfsCalculation(nums, operators);
    }
}
