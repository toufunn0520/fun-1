package category.design.datastructure;

import java.util.Stack;

public class BaseMaxStack implements MaxStack {

    private Stack<Integer> dataStack;

    private Stack<Integer> maxStack;

    public BaseMaxStack() {
        dataStack = new Stack<Integer>();
        maxStack = new Stack<Integer>();
    }

    /**
     * O(1)
     */
    @Override
    public int peek() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("Empty stack");
        }

        return dataStack.peek();
    }

    /**
     * O(1)
     */
    @Override
    public int peekMax() {
        if (maxStack.isEmpty()) {
            throw new RuntimeException("Empty stack");
        }

        return maxStack.peek();
    }

    /**
     * O(1)
     */
    @Override
    public int pop() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("Empty stack");
        }

        maxStack.pop();
        return dataStack.pop();
    }

    /**
     * O(N)
     */
    @Override
    public int popMax() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("Empty stack");
        }

        Stack<Integer> temp = new Stack<Integer>();
        int currentMax = maxStack.peek();

        while (dataStack.peek() != currentMax) {
            temp.push(dataStack.pop());
            maxStack.pop();
        }

        int prevMax = dataStack.pop();
        maxStack.pop();

        if (maxStack.isEmpty()) {
            currentMax = Integer.MIN_VALUE;
        } else {
            currentMax = maxStack.peek();
        }

        while (!temp.isEmpty()) {
            currentMax = Math.max(currentMax, temp.peek());
            dataStack.push(temp.pop());
            maxStack.push(currentMax);
        }

        return prevMax;
    }

    /**
     * O(1)
     */
    @Override
    public void push(int val) {
        dataStack.push(val);

        int currentMax = val;
        if (!maxStack.isEmpty() && maxStack.peek() > val) {
            currentMax = maxStack.peek();
        }

        maxStack.push(currentMax);
    }

}
