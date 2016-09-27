package category.linkedlist.multiplelists;

import java.util.PriorityQueue;
import java.util.Queue;

import category.linkedlist.ListNode;

public class MergeLists {

    /**
     * [Leetcode 23] https://leetcode.com/problems/merge-k-sorted-lists/
     * 
     * <pre>
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     * </pre>
     * 
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        Queue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, (node1, node2) -> (Integer.compare(node1.val,
                node2.val)));

        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while (!queue.isEmpty()) {
            ListNode peek = queue.poll();
            if (peek.next != null) {
                queue.offer(peek.next);
            }
            current.next = peek;
            current = current.next;
        }

        return dummy.next;
    }

    /**
     * [Leetcode 21] https://leetcode.com/problems/merge-two-sorted-lists/
     *
     * <pre>
     * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the
     * nodes of the first two lists.
     * </pre>
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode dump = new ListNode(0);
        ListNode current = dump;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                current.next = l2;
                l2 = l2.next;
            } else {
                current.next = l1;
                l1 = l1.next;
            }
            current = current.next;
        }

        while (l1 != null) {
            current.next = l1;
            current = current.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            current.next = l2;
            current = current.next;
            l2 = l2.next;
        }

        return dump.next;
    }
}
