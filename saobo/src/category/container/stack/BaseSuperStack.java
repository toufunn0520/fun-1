package category.container.stack;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

/**
 * [Zenefits] Implement a super stack have pop(), push(), peek(), inc(a, b) : would increase b from bottom for a nodes.
 */
public class BaseSuperStack implements SuperStack<Integer> {

    public static void main(String[] args) {
        BaseSuperStack bs = new BaseSuperStack();
        bs.push(1);
        bs.push(2);
        bs.push(3);
        bs.push(4);

        bs.inc(2, 10);

        for (int i = 0; i < 4; i++) {
            System.out.println(bs.pop());
        }

    }

    private StackNode head;

    private StackNode tail;

    public BaseSuperStack() {
        head = null;
        tail = null;
    }

    @Override
    public void inc(int num, int numToAdd) {
        StackNode current = tail;
        for (int i = 0; i < num; i++) {
            if (current == null) {
                break;
            }
            current.val += numToAdd;
            current = current.prev;
        }
    }

    @Override
    public Integer peek() {
        return head.val;
    }

    @Override
    public Integer pop() {
        if (head == null) {
            throw new NoSuchElementException("no such element");
        }

        int peek = head.val;
        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }

        return peek;
    }

    @Override
    public void push(Integer val) {
        StackNode newHead = new StackNode(val);

        if (head == null) {
            head = newHead;
            tail = head;
        } else {
            newHead.next = head;
            head.prev = newHead;
            head = newHead;
        }
    }

    @Test
    public void testForBaseCases() {
        BaseSuperStack stack = new BaseSuperStack();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }

        stack.inc(5, 100);

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(Integer.valueOf(9 - i), stack.peek());
            Assert.assertEquals(Integer.valueOf(9 - i), stack.pop());
        }

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(Integer.valueOf(104 - i), stack.peek());
            Assert.assertEquals(Integer.valueOf(104 - i), stack.pop());
        }
    }

    @Test
    public void testForSpecialCases() {
        BaseSuperStack stack = new BaseSuperStack();
        stack.push(100);

        Assert.assertEquals(Integer.valueOf(100), stack.peek());
        stack.inc(1, 100);
        Assert.assertEquals(Integer.valueOf(200), stack.peek());
    }

}
