package category.design.datastructure;

import java.util.Comparator;
import java.util.TreeSet;

public class ProMaxStack implements MaxStack {

    class Node {

        Node next;

        Node prev;

        long timestamp;

        int val;

        public Node(int val) {
            this.val = val;
            this.timestamp = System.nanoTime();
        }

        @Override
        public String toString() {
            return "" + val + " " + timestamp;
        }
    }

    public static void main(String[] args) {
        ProMaxStack maxStack = new ProMaxStack();
        maxStack.push(1);
        maxStack.push(10);
        maxStack.push(2);
        maxStack.push(10);
        maxStack.push(8);

        maxStack.popMax();
        maxStack.pop();
        maxStack.pop();
        maxStack.pop();
        maxStack.pop();
    }

    private Node currentMaxNode;

    private Node tail;

    private TreeSet<Node> values;

    public ProMaxStack() {
        values = new TreeSet<Node>(new Comparator<Node>() {

            /**
             * If two nodes have same value, compare their timestamp so that the earlier one is considerred smaller.
             */
            @Override
            public int compare(Node node1, Node node2) {
                if (node1.val == node2.val) {
                    return Long.compare(node1.timestamp, node2.timestamp);
                } else {
                    return Integer.compare(node1.val, node2.val);
                }
            }

        });

    }

    /**
     * O(1)
     */
    @Override
    public int peek() {
        if (values.isEmpty()) {
            throw new RuntimeException("Empty stack");
        }

        return tail.val;
    }

    /**
     * O(1)
     */
    @Override
    public int peekMax() {
        if (values.isEmpty()) {
            throw new RuntimeException("Empty stack");
        }

        return currentMaxNode.val;
    }

    /**
     * O(lgN)
     */
    @Override
    public int pop() {
        if (tail == null) {
            throw new RuntimeException("Empty stack");
        }

        Node nodeToPop = tail;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        }

        values.remove(nodeToPop);
        if (!values.isEmpty()) {
            currentMaxNode = values.last();
        }
        return nodeToPop.val;
    }

    /**
     * O(lgN)
     */
    @Override
    public int popMax() {
        if (tail == null) {
            throw new RuntimeException("Empty stack");
        }

        if (currentMaxNode == tail) {
            tail = tail.prev;
        }

        int popValue = removeNode(currentMaxNode);

        values.remove(currentMaxNode);

        if (!values.isEmpty()) {
            currentMaxNode = values.last();
        }

        return popValue;
    }

    /**
     * O(lgN)
     */
    @Override
    public void push(int val) {
        Node newNode = new Node(val);
        values.add(newNode);

        if (tail != null) {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;

        currentMaxNode = values.last();
    }

    private int removeNode(Node node) {

        if (node.prev != null) {
            node.prev.next = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        }

        return node.val;
    }

}
