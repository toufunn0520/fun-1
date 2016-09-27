package category.design.datastructure;

import java.util.HashMap;
import java.util.Map;

/**
 * [Leetcode 146] https://leetcode.com/problems/lru-cache/
 * 
 * <pre>
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations:
 * get and set.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should
 * invalidate the least recently used item before inserting a new item.
 * </pre>
 */
public class LRUCache {

    private class Node {

        int key;

        Node next;

        Node prev;

        int val;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Node)) {
                return false;
            } else {
                return ((Node) o).key == key;
            }
        }

        @Override
        public int hashCode() {
            return key;
        }
    }

    public static void main(String[] args) {
        LRUCache c = new LRUCache(2);

        c.set(2, 1);
        c.set(1, 1);
        System.out.println(c.get(2));
        c.set(4, 1);
        System.out.println(c.get(1));
        System.out.println(c.get(2));

    }

    private int capacity;

    private int count;

    private Node dummy;

    private Map<Integer, Node> key2Node;

    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;

        key2Node = new HashMap<Integer, Node>();
        dummy = new Node(0, 0);
        tail = dummy;
        count = 0;
    }

    public int get(int key) {
        if (key2Node.containsKey(key)) {
            Node current = key2Node.get(key);

            moveToTail(current);
            return current.val;
        } else {
            return -1;
        }
    }

    private void moveToTail(Node node) {
        if (node == tail) {
            return;
        }

        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        if (next != null) {
            next.prev = prev;
        }

        tail.next = node;
        node.prev = tail;

        tail = tail.next;
    }

    public void set(int key, int value) {
        Node current;

        if (key2Node.containsKey(key)) {
            current = key2Node.get(key);
            current.val = value;
            moveToTail(current);
        } else {
            if (count == capacity) {
                key2Node.remove(dummy.next.key);
                dummy.next = dummy.next.next;
                if (dummy.next != null) {
                    dummy.next.prev = dummy;
                }
                count--;
            }

            current = new Node(key, value);
            key2Node.put(key, current);
            tail.next = current;
            current.prev = tail;
            tail = tail.next;
            count++;
        }
    }

}
