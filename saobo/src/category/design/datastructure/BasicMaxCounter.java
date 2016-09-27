package category.design.datastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BasicMaxCounter implements MaxCounter {

    class Node {

        int count;

        Node next;

        Node prev;

        Set<Integer> values;

        public Node(int count, int value) {
            this.count = count;
            this.values = new HashSet<Integer>();
            this.values.add(value);
        }

        @Override
        public String toString() {
            return count + " " + values.toString();
        }
    }

    public static void main(String[] args) {
        MaxCounter c = new BasicMaxCounter();

        for (int i = 0; i < 100; i++) {
            c.add(1);
        }

        c.add(2);
        c.add(2);
        System.out.println(c.getMaxCount());
        c.removeMaxCount();
        System.out.println(c.getMaxCount());
        c.removeMaxCount();
        System.out.println(c.getMaxCount());
    }

    private Map<Integer, Node> count2Node;

    private Node head, tail;

    private Map<Integer, Integer> value2Count;

    public BasicMaxCounter() {
        head = new Node(0, 0);
        tail = head;
        value2Count = new HashMap<Integer, Integer>();
        count2Node = new HashMap<Integer, Node>();
    }

    @Override
    public void add(int val) {
        if (!value2Count.containsKey(val)) {
            // the first time the val shows up.
            if (count2Node.containsKey(1)) {
                count2Node.get(1).values.add(val);
            } else {
                updateNode(head, 1, val);
            }

            value2Count.put(val, 1);
        } else {
            int count = value2Count.get(val);
            Node node = count2Node.get(count);
            if (node.next != null && node.next.count == count + 1) {
                node.next.values.add(val);
            } else {
                updateNode(node, count + 1, val);
            }

            node.values.remove(val);
            if (node.values.size() == 0) {
                count2Node.remove(count);
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            value2Count.put(val, ++count);
        }
    }

    private Node addNode(Node prev, int newCount, int newVal) {
        Node newNode = new Node(newCount, newVal);

        Node next = prev.next;
        prev.next = newNode;
        newNode.next = next;
        newNode.prev = prev;
        if (next != null) {
            next.prev = newNode;
        }

        return newNode;
    }

    @Override
    public int getCount(int val) {
        if (value2Count.containsKey(val)) {
            return value2Count.get(val);
        } else {
            return 0;
        }
    }

    @Override
    public int getMaxCount() {
        return tail.count;
    }

    @Override
    public void removeMaxCount() {
        int maxCount = tail.count;
        if (maxCount == 0) {
            return;
        }

        int maxCountValue = tail.values.iterator().next();
        value2Count.remove(maxCountValue);

        tail.values.remove(maxCountValue);

        if (tail.values.size() == 0) {
            tail = tail.prev;
            count2Node.remove(maxCount);
        }
    }

    private void updateNode(Node prev, int newCount, int newValue) {
        Node newNode = addNode(prev, newCount, newValue);

        if (newNode.count > tail.count) {
            tail = newNode;
        }

        count2Node.put(newCount, newNode);
    }

}
