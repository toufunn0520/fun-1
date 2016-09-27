package category.linkedlist.singlelist;

import category.linkedlist.ListNode;

public class ReorderList {

    private void merge(ListNode head1, ListNode head2) {
        while (head1 != null && head2 != null) {
            ListNode next1 = head1.next;
            head1.next = head2;
            head2 = head2.next;
            head1.next.next = next1;
            head1 = next1;
        }
    }

    /**
     * [Leetcode 143] https://leetcode.com/problems/reorder-list/
     * 
     * <pre>
     * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
     * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
     *
     * You must do this in-place without altering the nodes' values.
     *
     * For example,
     * Given {1,2,3,4}, reorder it to {1,4,2,3}.
     * </pre>
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        ListNode slow = head, fast = head, prevSlow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prevSlow = slow;
            slow = slow.next;
        }

        ListNode reversedHead = reverse(slow.next);
        slow.next = null;

        merge(head, reversedHead);
    }

    private ListNode reverse(ListNode head) {

        ListNode prev = null, next = null, current = head;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }
}
