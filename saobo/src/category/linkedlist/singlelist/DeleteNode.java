package category.linkedlist.singlelist;

import category.linkedlist.ListNode;

public class DeleteNode {

    /**
     * [Leetcode 237] https://leetcode.com/problems/delete-node-in-a-linked-list/
     * 
     * <pre>
     * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
     * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list
     * should become 1 -> 2 -> 4 after calling your function.
     * </pre>
     *
     * @param node
     */
    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);

        head.print();

        System.out.println("~~~~~~");

        deleteNode(head.next.next.next);

        head.print();
    }

    /**
     * [Leetcode 203] https://leetcode.com/problems/remove-linked-list-elements/
     *
     * <pre>
     * Remove all elements from a linked list of integers that have value val.
     * 
     * Example
     * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
     * Return: 1 --> 2 --> 3 --> 4 --> 5
     * </pre>
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null) {

            while (head != null && head.val == val) {
                head = head.next;
            }

            prev.next = head;
            prev = head;

            if (head != null)
                head = head.next;
        }

        return dummy.next;
    }

    /**
     * [Leetcode 19] https://leetcode.com/problems/remove-nth-node-from-end-of-list/
     *
     * <pre>
     * Given a linked list, remove the nth node from the end of list and return its head.
     * 
     * For example,
     * 
     *    Given linked list: 1->2->3->4->5, and n = 2.
     * 
     *    After removing the second node from the end, the linked list becomes 1->2->3->5.
     * Note:
     * Given n will always be valid.
     * Try to do this in one pass.
     * </pre>
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return null;

        ListNode beforeHead = new ListNode(0);
        beforeHead.next = head;
        ListNode first = beforeHead;
        ListNode second = beforeHead;

        while (n > 0 && second.next != null) {
            second = second.next;
            n--;
        }

        while (second.next != null) {
            first = first.next;
            second = second.next;
        }

        first.next = first.next.next;
        return beforeHead.next;
    }
}
