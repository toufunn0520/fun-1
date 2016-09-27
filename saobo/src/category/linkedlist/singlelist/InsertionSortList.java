package category.linkedlist.singlelist;

import category.linkedlist.ListNode;

public class InsertionSortList {

    /**
     * [Leetcode 147] https://leetcode.com/problems/insertion-sort-list/
     * 
     * <pre>
     * Sort a linked list using insertion sort.
     * </pre>
     * 
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode newDummy = new ListNode(0);
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode newCurrent = newDummy;
        ListNode prev = dummy;
        ListNode current = head;
        ListNode minNode = current;
        ListNode minPrevNode = dummy;

        while (current != null) {
            while (current != null) {
                if (minNode == null || current.val < minNode.val) {
                    minNode = current;
                    minPrevNode = prev;
                }

                prev = current;
                current = current.next;
            }

            newCurrent.next = minNode;
            newCurrent = newCurrent.next;

            minPrevNode.next = minPrevNode.next.next;
            current = dummy.next;
            minNode = null;
            prev = dummy;
        }

        return newDummy.next;
    }
}
