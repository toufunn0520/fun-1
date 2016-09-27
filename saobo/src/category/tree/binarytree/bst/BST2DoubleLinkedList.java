package category.tree.binarytree.bst;

import java.util.Stack;

import category.tree.binarytree.properties.TreeNode;

public class BST2DoubleLinkedList {

    public static ListNode convert(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();

        ListNode dummy = new ListNode(-1);
        ListNode currentNode = dummy;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.add(current);
                current = current.left;
            } else {
                current = stack.pop();

                ListNode newNode = new ListNode(current.val);
                currentNode.next = newNode;
                newNode.prev = currentNode;
                currentNode = currentNode.next;
                current = current.right;
            }
        }

        ListNode head = dummy.next;
        head.prev = null;

        return head;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(1);
        root.right.right = new TreeNode(30);

        ListNode head = convert(root);

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}

class ListNode {

    ListNode prev, next;

    int val;

    ListNode(int val) {
        this.val = val;
    }
}
