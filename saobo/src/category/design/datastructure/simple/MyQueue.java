package category.design.datastructure.simple;

import java.util.Stack;

/**
 * [Leetcode 232] https://leetcode.com/problems/implement-queue-using-stacks/
 * 
 * <pre>
 * Implement the following operations of a queue using stacks.
 * 
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * Notes:
 * You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
 * You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 * </pre>
 */
public class MyQueue {

    private Stack<Integer> incomingStack;

    private Stack<Integer> outgoingStack;

    public MyQueue() {
        incomingStack = new Stack<Integer>();
        outgoingStack = new Stack<Integer>();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return incomingStack.isEmpty() && outgoingStack.isEmpty();
    }

    // Get the front element.
    public int peek() {
        if (outgoingStack.isEmpty()) {
            while (!incomingStack.isEmpty()) {
                outgoingStack.push(incomingStack.pop());
            }
        }

        return outgoingStack.peek();
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (outgoingStack.isEmpty()) {
            while (!incomingStack.isEmpty()) {
                outgoingStack.push(incomingStack.pop());
            }
        }

        outgoingStack.pop();
    }

    public void push(int x) {
        incomingStack.push(x);
    }
}
