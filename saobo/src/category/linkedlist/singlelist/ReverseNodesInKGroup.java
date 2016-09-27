package category.linkedlist.singlelist;

import category.linkedlist.ListNode;

public class ReverseNodesInKGroup {

    public static void main(String[] args) {

        ReverseNodesInKGroup solution = new ReverseNodesInKGroup();

        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);

        ListNode newHead = solution.reverseKGroup(head, 3);

        newHead.print();
    }

    /**
     * Return the new tail after reverse.
     *
     * @param prevStart
     * @param postEnd
     * @return
     */
    private ListNode reverse(ListNode prevStart, ListNode postEnd) {
        if (prevStart == null || prevStart.next == null)
            return prevStart;

        ListNode newTail = prevStart.next;
        ListNode current = prevStart.next;
        ListNode next = null, prev = postEnd;
        while (current != postEnd) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        prevStart.next = prev;
        return newTail;
    }

    /**
     * [Leetcode 25] https://leetcode.com/problems/reverse-nodes-in-k-group/
     *
     * <pre>
     * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
     * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
     * You may not alter the values in the nodes, only nodes itself may be changed.
     * Only constant memory is allowed.
     *
     * For example,
     * Given this linked list: 1->2->3->4->5
     *
     * For k = 2, you should return: 2->1->4->3->5
     * For k = 3, you should return: 3->2->1->4->5
     * </pre>
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int count = 0;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            count++;
            ListNode next = cur.next;
            if (count == k) {
                pre = reverse(pre, next);
                count = 0;
            }
            cur = next;
        }
        return dummy.next;
    }

}
