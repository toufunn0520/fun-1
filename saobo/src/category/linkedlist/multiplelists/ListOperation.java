package category.linkedlist.multiplelists;

import category.linkedlist.ListNode;

public class ListOperation {

    public static ListNode add(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }

        head1 = reverse(head1);
        head2 = reverse(head2);

        ListNode resultDummy = new ListNode(-1);
        ListNode current = resultDummy;

        int carry = 0;
        while (head1 != null || head2 != null) {
            if (head1 != null) {
                carry += head1.val;
                head1 = head1.next;
            }

            if (head2 != null) {
                carry += head2.val;
                head2 = head2.next;
            }

            current.next = new ListNode(carry % 10);
            current = current.next;

            carry /= 10;
        }

        if (carry > 0) {
            current.next = new ListNode(carry);
            current = current.next;
        }

        return reverse(resultDummy.next);
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(9);
        head1.next = new ListNode(9);
        head1.next.next = new ListNode(9);

        ListNode head2 = new ListNode(9);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(9);

        ListNode result = add(null, null);

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    /**
     * [Leetcode 2] https://leetcode.com/problems/add-two-numbers/
     * <pre>
     * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and
     * each of their nodes contain a single digit. Add the two numbers and return it as a linked list. 
     * 
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) 
     * Output: 7 -> 0 -> 8
     * </pre>
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;

        ListNode newHead = new ListNode(0);
        ListNode p1 = l1, p2 = l2, p3 = newHead;

        while (p1 != null || p2 != null) {
            if (p1 != null) {
                carry += p1.val;
                p1 = p1.next;
            }

            if (p2 != null) {
                carry += p2.val;
                p2 = p2.next;
            }

            p3.next = new ListNode(carry % 10);
            p3 = p3.next;
            carry /= 10;
        }

        if (carry == 1)
            p3.next = new ListNode(1);

        return newHead.next;
    }
}
