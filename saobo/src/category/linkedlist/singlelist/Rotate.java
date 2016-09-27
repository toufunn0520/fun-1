package category.linkedlist.singlelist;

import category.linkedlist.ListNode;

public class Rotate {

    /**
     * [Leetcode 61] https://leetcode.com/problems/rotate-list/
     * 
     * <pre>
     * Given a list, rotate the list to the right by k places, where k is non-negative.
     *
     * For example:
     * Given 1->2->3->4->5->NULL and k = 2,
     * return 4->5->1->2->3->NULL.
     * </pre>
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        int count = 1;
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
            count++;
        }
        current.next = head;

        int moveStepsFromHead = count - k % count - 1;
        count = 0;
        current = head;
        while (count < moveStepsFromHead) {
            current = current.next;
            count++;
        }

        ListNode newHead = current.next;
        current.next = null;
        return newHead;
    }

}
