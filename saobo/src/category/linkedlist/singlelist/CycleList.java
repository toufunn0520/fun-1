package category.linkedlist.singlelist;

import org.junit.Assert;
import org.junit.Test;

import category.linkedlist.ListNode;
import category.linkedlist.MyList;

public class CycleList implements MyList {

    public static void main(String[] args) {
        CycleList c = new CycleList();
        c.insert(1);
        c.insert(2);
        c.remove(2);
        c.print();
    }

    ListNode head;

    public CycleList() {
        head = null;
    }

    @Override
    public ListNode insert(int val) {
        if (head == null) {
            head = new ListNode(val);
            head.next = head;
            return head;
        }

        ListNode current = head;
        while (current.next != head && val >= current.val) {
            current = current.next;
        }

        ListNode next = current.next;
        current.next = new ListNode(val);
        current.next.next = next;

        if (val < head.val) {
            head = current.next;
        }

        return head;
    }

    @Override
    public void print() {
        if (head != null) {
            head.print();
        } else {
            System.out.println("list with null");
        }
    }

    @Override
    public ListNode remove(int val) {
        if (head == null || val < head.val) {
            // no such element
            return head;
        }

        ListNode tail = head;
        while (tail.next != head) {
            tail = tail.next;
        }

        if (tail.val < val) {
            // no such element
            return head;
        }

        // break cycle to single linkedlist
        tail.next = null;

        if (head.val == val) {
            // nodes with the head's val will be removed
            while (head != null && head.val == val) {
                head = head.next;
            }

            if (head != null) {
                // have something left after remove
                tail.next = head;
            }
        } else {
            // head would not be removed
            ListNode prev = head; // this is the prev node before first removed node
            ListNode current = head.next;
            while (current != null && current.val < val) {
                prev = current;
                current = current.next;
            }

            while (current != null && current.val == val) {
                current = current.next;
            }

            if (current != null) {
                // has nodes with greater val left
                prev.next = current;
                current.next = head;
            } else {
                // don't have nodes greater than val left.
                prev.next = head;
            }
        }

        return head;
    }

    @Test
    public void testWithMultipleNodesWithDifferentValuesInsertAndHeadRemove() {
        CycleList list = new CycleList();

        Assert.assertEquals(null, list.head);
        list.insert(1);
        list.insert(2);
        list.insert(3);
        Assert.assertEquals(1, list.head.val);
        Assert.assertEquals(2, list.head.next.val);
        Assert.assertEquals(3, list.head.next.next.val);
        Assert.assertEquals(list.head, list.head.next.next.next);
        list.remove(1);
        Assert.assertEquals(2, list.head.val);
        Assert.assertEquals(3, list.head.next.val);
        Assert.assertEquals(2, list.head.next.next.val);
        Assert.assertEquals(list.head, list.head.next.next);
    }

    @Test
    public void testWithMultipleNodesWithDifferentValuesInsertAndMiddleRemove() {
        CycleList list = new CycleList();

        Assert.assertEquals(null, list.head);
        list.insert(1);
        list.insert(2);
        list.insert(2);
        list.insert(3);
        Assert.assertEquals(1, list.head.val);
        Assert.assertEquals(2, list.head.next.val);
        Assert.assertEquals(2, list.head.next.next.val);
        Assert.assertEquals(3, list.head.next.next.next.val);
        Assert.assertEquals(list.head, list.head.next.next.next.next);
        list.remove(2);
        Assert.assertEquals(1, list.head.val);
        Assert.assertEquals(3, list.head.next.val);
        Assert.assertEquals(1, list.head.next.next.val);
        Assert.assertEquals(list.head, list.head.next.next);
    }

    @Test
    public void testWithMultipleNodesWithDifferentValuesInsertAndTailRemove() {
        CycleList list = new CycleList();

        Assert.assertEquals(null, list.head);
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(3);
        Assert.assertEquals(1, list.head.val);
        Assert.assertEquals(2, list.head.next.val);
        Assert.assertEquals(3, list.head.next.next.val);
        Assert.assertEquals(3, list.head.next.next.next.val);
        Assert.assertEquals(list.head, list.head.next.next.next.next);
        list.remove(3);
        Assert.assertEquals(1, list.head.val);
        Assert.assertEquals(2, list.head.next.val);
        Assert.assertEquals(1, list.head.next.next.val);
        Assert.assertEquals(list.head, list.head.next.next);
    }

    @Test
    public void testWithMultipleNodesWithSameValueInsertAndRemove() {
        CycleList list = new CycleList();

        Assert.assertEquals(null, list.head);
        list.insert(1);
        list.insert(1);
        list.insert(1);
        Assert.assertEquals(1, list.head.val);
        Assert.assertEquals(1, list.head.next.val);
        Assert.assertEquals(1, list.head.next.next.val);
        Assert.assertEquals(list.head, list.head.next.next.next);
        list.remove(1);
        Assert.assertEquals(null, list.head);
    }

    @Test
    public void testWithOneNodeInsertAndRemove() {
        CycleList list = new CycleList();

        Assert.assertEquals(null, list.head);
        list.insert(1);
        Assert.assertEquals(1, list.head.val);
        Assert.assertEquals(list.head, list.head.next);
        list.remove(1);
        Assert.assertEquals(null, list.head);
    }

    @Override
    public String toString() {
        return head.toString();
    }
}
