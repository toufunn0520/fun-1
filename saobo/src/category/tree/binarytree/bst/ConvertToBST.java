package category.tree.binarytree.bst;

import category.linkedlist.ListNode;
import category.tree.binarytree.properties.TreeNode;

public class ConvertToBST {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        int[] nums = { 1, 2, 3, 4, 5 };

        TreeNode root = new ConvertToBST().sortedArrayToBST(nums);

        root.print();

    }

    /**
     * [Leetcode 108] https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
     *
     * <pre>
     * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     * </pre>
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBSTHelper(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBSTHelper(nums, start, mid - 1);
        root.right = sortedArrayToBSTHelper(nums, mid + 1, end);

        return root;
    }

    /**
     * [Leetcode 109] https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
     * 
     * <pre>
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     * </pre>
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        int count = 0;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            count++;
        }

        return sortedListToBSTHelper(head, count);
    }

    private TreeNode sortedListToBSTHelper(ListNode head, int length) {
        if (head == null || length == 0) {
            return null;
        }

        int movingSteps = length / 2;
        ListNode current = head;
        while (movingSteps != 0) {
            current = current.next;
            movingSteps--;
        }

        TreeNode root = new TreeNode(current.val);

        root.left = sortedListToBSTHelper(head, length / 2);
        root.right = sortedListToBSTHelper(current.next, length - 1 - length / 2);

        return root;
    }
}
