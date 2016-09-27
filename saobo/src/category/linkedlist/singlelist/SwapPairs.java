package category.linkedlist.singlelist;

import category.linkedlist.ListNode;

public class SwapPairs {

    /**
     * [Leetcode 24] https://leetcode.com/problems/swap-nodes-in-pairs/
     * 
     * <pre>
     * Given a linked list, swap every two adjacent nodes and return its head. For example, Given 1->2->3->4, you should
     * return the list as 2->1->4->3. Your algorithm should use only constant space. You may not modify the values in
     * the list, only nodes itself can be changed.
     * </pre>
     * 
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode previous = dummy;

        while (head != null && head.next != null) {
            previous.next = head.next;
            head.next = head.next.next;
            previous.next.next = head;
            previous = head;
            head = head.next;
        }
        return dummy.next;
    }

}
