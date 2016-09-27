package category.tree.binarytree.operations;

import category.tree.binarytree.properties.TreeNode;

class ListNode {

    ListNode next;

    int val;

    public ListNode(int val) {
        this.val = val;
    }

}

public class MergeBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        // root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(30);

        TreeNode root2 = new TreeNode(18);
        root2.left = new TreeNode(14);
        root2.right = new TreeNode(25);
        root2.left.left = new TreeNode(8);
        root2.left.right = new TreeNode(16);

        TreeNode newRoot = new MergeBST().merge(root, root2);
        newRoot.print();

    }

    private ListNode BST2List(TreeNode root) {
        if (root == null) {
            return null;
        }

        ListNode node = new ListNode(root.val);
        ListNode left = BST2List(root.left);
        ListNode right = BST2List(root.right);

        node.next = right;

        if (left == null) {
            return node;
        } else {
            ListNode current = left;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }

        return left;
    }

    private TreeNode list2BST(ListNode head, int count) {
        if (head == null || count == 0) {
            return null;
        }

        ListNode current = head;

        int movingSteps = count / 2;

        while (movingSteps > 0) {
            current = current.next;
            movingSteps--;
        }

        TreeNode root = new TreeNode(current.val);
        root.left = list2BST(head, count / 2);
        root.right = list2BST(current.next, count - count / 2 - 1);

        return root;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (head1 != null || head2 != null) {
            if (head1 == null) {
                current.next = head2;
                head2 = head2.next;
            } else if (head2 == null) {
                current.next = head1;
                head1 = head1.next;
            } else {
                if (head1.val < head2.val) {
                    current.next = head1;
                    head1 = head1.next;
                } else {
                    current.next = head2;
                    // dedupe
                    if (head1.val == head2.val) {
                        head1 = head1.next;
                    }
                    head2 = head2.next;
                }
            }
            current = current.next;
        }

        return dummy.next;
    }

    /**
     * You are given two binary search trees. Write a function that merges the two given balanced BSTs into a balanced
     * binary search tree. Your merge function should take O(M+N) time and O(1) space.
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode merge(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }

        if (root2 == null) {
            return root1;
        }

        ListNode head1 = BST2List(root1);
        ListNode head2 = BST2List(root2);

        ListNode newHead = merge(head1, head2);

        int count = 0;
        ListNode current = newHead;
        while (current != null) {
            current = current.next;
            count++;
        }

        return list2BST(newHead, count);
    }

}
