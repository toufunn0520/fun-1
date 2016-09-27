package category.linkedlist.multiplelists;

import category.linkedlist.ListNode;

public class IntersectionOfTwoLinkedLists {

    /**
     * [Leetcode 160] https://leetcode.com/problems/intersection-of-two-linked-lists/
     * 
     * <pre>
     * Write a program to find the node at which the intersection of two singly linked lists begins.
     *
     * For example, the following two linked lists:
     *
     * A:          a1 → a2
     *                    ↘
     *                      c1 → c2 → c3
     *                    ↗
     * B:     b1 → b2 → b3
     * begin to intersect at node c1.
     *
     *
     * Notes:
     *
     * If the two linked lists have no intersection at all, return null.
     * The linked lists must retain their original structure after the function returns.
     * You may assume there are no cycles anywhere in the entire linked structure.
     * Your code should preferably run in O(n) time and use only O(1) memory.
     * </pre>
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int countA = getLength(headA);
        int countB = getLength(headB);

        ListNode nodeA = headA;
        ListNode nodeB = headB;

        while (countA > countB) {
            nodeA = nodeA.next;
            countA--;
        }

        while (countB > countA) {
            nodeB = nodeB.next;
            countB--;
        }

        while (nodeA != null) {
            if (nodeA == nodeB) {
                return nodeA;
            } else {
                nodeA = nodeA.next;
                nodeB = nodeB.next;
            }
        }

        return null;
    }

    private int getLength(ListNode head) {
        int count = 0;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }
}
