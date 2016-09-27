package category.design.datastructure.simple;

import java.util.LinkedList;
import java.util.Queue;

/**
 * [Leetcode 225] https://leetcode.com/problems/implement-stack-using-queues/
 * 
 * <pre>
 * Implement the following operations of a stack using queues.
 * 
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * Notes:
 * You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty
 * operations are valid. Depending on your language, queue may not be supported natively. You may simulate a queue by using a
 * list or deque (double-ended queue), as long as you use only standard operations of a queue. You may assume that all operations
 * are valid (for example, no pop or top operations will be called on an empty stack).
 * </pre>
 */
public class MyStack {

    public static void main(String[] args) {
        MyStack s = new MyStack();

        s.push(1);
        s.push(2);

        System.out.println(s.top());
        s.pop();
        System.out.println(s.top());

    }

    private Queue<Integer> outQueue;

    private Queue<Integer> tempQueue;

    public MyStack() {
        tempQueue = new LinkedList<Integer>();
        outQueue = new LinkedList<Integer>();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return outQueue.isEmpty();
    }

    // Removes the element on top of the stack.
    public void pop() {
        outQueue.poll();
    }

    // Push element x onto stack.
    public void push(int x) {

        while (!outQueue.isEmpty()) {
            tempQueue.offer(outQueue.poll());
        }

        outQueue.offer(x);
        while (!tempQueue.isEmpty()) {
            outQueue.offer(tempQueue.poll());
        }
    }

    // Get the top element.
    public int top() {
        return outQueue.peek();
    }
}
