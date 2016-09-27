package category.container.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class MiniParser {

    private static Numbers getNumbers(List<Object> objects) {
        Stack<Object> stack = new Stack<Object>();

        for (Object s : objects) {
            if (s.equals("]")) {
                Stack<Object> temp = new Stack<Object>();
                while (!stack.peek().equals("[")) {
                    temp.push(stack.pop());
                }
                // pop "["
                stack.pop();

                List<Numbers> numbers = new ArrayList<Numbers>();
                while (!temp.isEmpty()) {
                    numbers.add((Numbers) temp.pop());
                }

                stack.push(new Numbers(numbers));
            } else {
                stack.push(s);
            }
        }

        return (Numbers) stack.pop();
    }

    /**
     * [Air*bnb] parse string "[123,456,[788,799,833],[[]],10,[]]" into a structure.
     *
     * @param s
     * @return
     */
    public static Numbers getNumbers(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        StringTokenizer tokenizer = new StringTokenizer(s, ",|\\[|\\]", true);

        List<Object> filtered = new ArrayList<Object>();
        while (tokenizer.hasMoreTokens()) {
            String current = tokenizer.nextToken();
            if (!current.equals(",")) {
                try {
                    int i = Integer.parseInt(current);
                    Numbers number = new Numbers(i);
                    filtered.add(number);
                } catch (NumberFormatException e) {
                    filtered.add(current);
                }
            }
        }

        return getNumbers(filtered);
    }

    public static void main(String[] args) {
        String s = "[123,456,[788,799,833],[[]],10,[]]";

        System.out.println(getNumbers(s));

        String t = "abc";
        System.out.println(t.substring(3));

    }

}

class Numbers {

    boolean isNumber;

    List<Numbers> numbers;

    int val;

    public Numbers(int val) {
        this.isNumber = true;
        this.val = val;
    }

    public Numbers(List<Numbers> list) {
        this.isNumber = false;
        this.numbers = list;
    }

    @Override
    public String toString() {
        return isNumber ? "{" + val + "}" : "{" + numbers.toString() + "}";
    }
}
