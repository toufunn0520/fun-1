package category.design.datastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * [snamp*chat] insert(int val), getRandom(), delete(int val) 均在 O(1) time
 *
 * @author boyi
 */
public class RandomElementFetcher {

    class Node {

        int index;

        Node prev, next;

        int val;

        Node(int index, int val) {
            this.index = index;
            this.val = val;
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(new Random().nextInt(2));
        }
    }

    private Map<Integer, Node> index2Node;

    private Node tail;

    private Map<Integer, Node> val2Node;

    public RandomElementFetcher() {
        val2Node = new HashMap<Integer, Node>();
        index2Node = new HashMap<Integer, Node>();
        Node dummy = new Node(0, -1);
        tail = dummy;
    }

    public int getRandom() {
        int size = index2Node.size();
        int random = new Random().nextInt(size);
        return index2Node.get(random).val;
    }

    public boolean insert(int val) {
        if (val2Node.containsKey(val)) {
            return false;
        } else {
            Node newNode = new Node(index2Node.size(), val);
            val2Node.put(val, newNode);
            index2Node.put(index2Node.size(), newNode);
            tail.next = newNode;
            newNode.prev = tail;
            tail = tail.next;
            return true;
        }
    }

    public boolean remove(int val) {
        if (val2Node.containsKey(val)) {
            Node node = val2Node.get(val);
            val2Node.remove(val);
            index2Node.remove(index2Node.size() - 1);

            if (node == tail) {
                tail = tail.prev;
                tail.next = null;
            } else if (node.next == tail) {
                tail.prev = tail.prev.prev;
                tail.prev.next = tail;
            } else {
                Node prevTail = tail;
                tail = tail.prev;
                tail.next = null;

                node.prev.next = prevTail;
                prevTail.next = node.next;
                node.next.prev = prevTail;
                prevTail.prev = node.prev;
                prevTail.index = node.index;
                index2Node.put(node.index, prevTail);
            }

            return true;
        } else {
            return false;
        }
    }

}
