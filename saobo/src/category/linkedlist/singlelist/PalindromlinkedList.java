package category.linkedlist.singlelist;

import category.linkedlist.ListNode;

public class PalindromlinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        // head.next.next.next.next = new ListNode(1);

        head.print();

        System.out.println(new PalindromlinkedList().isPalindrome(head));

    }

    public int getLength(ListNode head) {
        int count = 0;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    /**
     * [Leetcode 234] https://leetcode.com/problems/palindrome-linked-list/
     * 
     * <pre>
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     *
     * For example,
     * "A man, a plan, a canal: Panama" is a palindrome.
     * "race a car" is not a palindrome.
     *
     * Note:
     * Have you consider that the string might be empty? This is a good question to ask during an interview.
     *
     * For the purpose of this problem, we define empty string as valid palindrome.
     * </pre>
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        int len = getLength(head);
        ListNode head2 = head;
        int count = 0;
        while (head2 != null && count < len - len / 2) {
            head2 = head2.next;
            count++;
        }

        ListNode current = head;
        ListNode current2 = reverse(head2);

        count = 0;
        while (count < len / 2) {
            if (current.val != current2.val) {
                return false;
            } else {
                count++;
                current = current.next;
                current2 = current2.next;
            }
        }

        return true;
    }

    private ListNode reverse(ListNode node) {
        if (node == null) {
            return null;
        }

        ListNode current = node;
        ListNode next = node.next;
        ListNode prev = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }
}
