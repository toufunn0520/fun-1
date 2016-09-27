package category.design.datastructure;

import java.util.Stack;

/**
 * [Leetcode 155] https://leetcode.com/problems/min-stack/
 *
 * <pre>
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * 
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * </pre>
 */
public class MinStack {

    Stack<Integer> mins = new Stack<Integer>();

    Stack<Integer> stack = new Stack<Integer>();

    public int getMin() {
        return mins.peek();
    }

    public void pop() {
        stack.pop();
        mins.pop();
    }

    public void push(int x) {
        stack.push(x);
        if (mins.isEmpty() || mins.peek() > x) {
            mins.push(x);
        } else {
            mins.push(mins.peek());
        }
    }

    public int top() {
        return stack.peek();
    }

}
