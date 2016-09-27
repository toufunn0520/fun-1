package category.design.datastructure.simple;

import java.util.HashMap;
import java.util.Map;

class Node {

    int index;

    Node prev, next;

    int val;

    Node(int index, int val) {
        this.val = val;
        this.index = index;
    }

}

/**
 * [Facebook] dot multiply sparse vector
 */
public class SparseVector {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public Node head;

    private Map<Integer, Node> index2Node;

    public SparseVector() {
        head = new Node(-1, -1);
        index2Node = new HashMap<Integer, Node>();
    }

    public void add(int index, int val) {
        if (index2Node.containsKey(index)) {
            index2Node.get(index).val += val;
        } else {
            Node current = head;
            while (current.next != null && index > current.next.index) {
                current = current.next;
            }
            Node next = current.next;
            Node newNode = new Node(index, val);
            current.next = newNode;
            newNode.next = next;
            next.prev = newNode;
            newNode.prev = current;

            index2Node.put(index, newNode);
        }
    }

    public int dotMultiply(SparseVector vector) {
        if (vector == null || vector.head == null) {
            return 0;
        }

        Node current1 = head.next;
        Node current2 = vector.head.next;

        int result = 0;
        while (current1 != null && current2 != null) {
            if (current1.index == current2.index) {
                result += current1.val * current2.val;
                current1 = current1.next;
                current2 = current2.next;
            } else if (current1.index < current2.index) {
                current1 = current1.next;
            } else {
                current2 = current2.next;
            }
        }

        return result;
    }

}
