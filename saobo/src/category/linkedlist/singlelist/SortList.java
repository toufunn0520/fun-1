package category.linkedlist.singlelist;

import category.linkedlist.ListNode;

public class SortList {

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(19);
        head.next.next = new ListNode(14);
        // head.next.next.next = new ListNode(5);

        head = new SortList().sortList(head);

        head.print();
    }

    private ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (l1 != null || l2 != null) {
            if (l1 == null && l2 != null) {
                tail.next = l2;
                return dummy.next;
            } else if (l1 != null && l2 == null) {
                tail.next = l1;
                return dummy.next;
            } else if (l1.val < l2.val) {
                tail.next = l1;
                tail = tail.next;
                l1 = l1.next;
            } else {
                tail.next = l2;
                tail = tail.next;
                l2 = l2.next;
            }
        }

        return dummy.next;
    }

    /**
     * [Leetcode 148] https://leetcode.com/problems/sort-list/
     * 
     * <pre>
     * Sort a linked list in O(n log n) time using constant space complexity.
     * </pre>
     * 
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // find the middle of the list
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode l1 = head;
        ListNode l2 = slow.next;
        slow.next = null;

        l1 = sortList(l1);
        l2 = sortList(l2);

        return mergeList(l1, l2);
    }
}
