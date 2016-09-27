package category.linkedlist.singlelist;

import category.linkedlist.ListNode;

public class DeleteDuplicate {

    /**
     * [Leetcode 83] https://leetcode.com/problems/remove-duplicates-from-sorted-list/
     *
     * <pre>
     * Given a sorted linked list, delete
     * all duplicates such that each element appear only once. For example, Given 1->1->2, return 1->2. Given
     * 1->1->2->3->3, return 1->2->3.
     * </pre>
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int current = head.val;
        ListNode prev = head;
        head = head.next;

        while (head != null) {
            if (current != head.val) {
                prev.next = head;
                current = head.val;
                prev = head;
            }
            head = head.next;

            if (head == null) {
                prev.next = null;
            }
        }

        return dummy.next;
    }

    /**
     * [Leetcode 82] https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
     *
     * <pre>
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original
     * list.
     *
     * For example,
     * Given 1->2->3->3->4->4->5, return 1->2->5.
     * Given 1->1->1->2->3, return 2->3.
     * </pre>
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(head.val - 1);
        dummy.next = head;
        ListNode current = head, previous = dummy;

        while (current != null) {
            if (current.next != null && current.next.val == current.val) {
                int duplicateVal = current.val;
                while (current != null && current.val == duplicateVal) {
                    current = current.next;
                }
                previous.next = current;
                continue;
            }

            previous.next = current;

            if (current != null) {
                previous = current;
                current = current.next;
            }
        }

        return dummy.next;
    }
}
