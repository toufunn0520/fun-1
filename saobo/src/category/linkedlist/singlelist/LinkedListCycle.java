package category.linkedlist.singlelist;

import category.linkedlist.ListNode;

public class LinkedListCycle {

    /**
     * [Leetcode 142] https://leetcode.com/problems/linked-list-cycle-ii/
     * 
     * <pre>
     * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
     *
     * Note: Do not modify the linked list.
     *
     * Follow up:
     * Can you solve it without using extra space?
     * </pre>
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode fast = head.next.next, slow = head.next;

        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                hasCycle = true;
                break;
            } else {
                fast = fast.next.next;
                slow = slow.next;
            }
        }

        if (!hasCycle) {
            return null;
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    /**
     * [Leetcode 141] https://leetcode.com/problems/linked-list-cycle/
     *
     * <pre>
     * Given a linked list, determine if it has a cycle in it.
     *
     * Follow up:
     * Can you solve it without using extra space?
     * </pre>
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode first = head;
        ListNode second = head.next.next;

        while (second != null && second.next != null) {
            if (first == second) {
                return true;
            } else {
                first = first.next;
                second = second.next.next;
            }
        }

        return false;
    }
}
