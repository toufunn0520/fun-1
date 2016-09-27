package category.linkedlist.singlelist;

import category.linkedlist.ListNode;

public class PartitionList {

    /**
     * [Leetcode 86] https://leetcode.com/problems/partition-list/
     * 
     * <pre>
     * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
     *
     * You should preserve the original relative order of the nodes in each of the two partitions.
     *
     * For example,
     * Given 1->4->3->2->5->2 and x = 3,
     * return 1->2->2->4->3->5.
     * </pre>
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }

        ListNode dummyTail = new ListNode(-1);
        ListNode dummyHead = new ListNode(-1);
        ListNode tailPart = dummyTail, headPart = dummyHead;
        ;

        ListNode current = head;
        while (current != null) {
            if (current.val < x) {
                headPart.next = current;
                headPart = current;
            } else {
                tailPart.next = current;
                tailPart = tailPart.next;
            }

            current = current.next;
        }

        tailPart.next = null;

        if (dummyHead.next == null) {
            return dummyTail.next;
        } else {
            headPart.next = dummyTail.next;
            return dummyHead.next;
        }
    }
}
