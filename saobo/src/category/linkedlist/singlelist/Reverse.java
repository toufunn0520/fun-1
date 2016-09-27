package category.linkedlist.singlelist;

import category.linkedlist.ListNode;

public class Reverse {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        a.print();
        ListNode reversed = new Reverse().reverseBetween(a, 2, 4);
        reversed.print();
    }

    /**
     * [Leetcode 206] https://leetcode.com/problems/reverse-linked-list/
     * 
     * <pre>
     * Reverse a singly linked list.
     * </pre>
     * 
     * @param head
     * @return
     */
    public ListNode iterateReverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode current = head, prev = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public ListNode recursiveReverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = recursiveReverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * [Leetcode 92] https://leetcode.com/problems/reverse-linked-list-ii/
     *
     * <pre>
     * Reverse a linked list from position m to n. Do it in-place and in one-pass.
     * 
     * For example:
     * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
     * 
     * return 1->4->3->2->5->NULL.
     * 
     * Note:
     * Given m, n satisfy the following condition:
     * 1 ≤ m ≤ n ≤ length of list.
     * </pre>
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }

        int count = 0;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode current = dummy, prev = null;
        while (count < m) {
            prev = current;
            current = current.next;
            count++;
        }

        ListNode prevStart = prev;
        ListNode newTail = current;
        while (count <= n) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        newTail.next = current;
        prevStart.next = prev;

        return dummy.next;
    }
}
