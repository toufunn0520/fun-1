package category.tree.binarytree.bst;

import category.tree.binarytree.properties.TreeNode;

public class RecoverBST {

    private TreeNode first, second;

    private TreeNode prev;

    private void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        inorderTraversal(node.left);

        if (prev != null && node.val < prev.val) {
            if (first == null) {
                first = prev;
                second = node;
            } else {
                second = node;
            }
        }

        prev = node;

        inorderTraversal(node.right);
    }

    /**
     * [Leetcode 99] https://leetcode.com/problems/recover-binary-search-tree/
     * 
     * <pre>
     * Two elements of a binary search tree (BST) are swapped by mistake.
     * Recover the tree without changing its structure. Note: A solution using
     * O(n) space is pretty straight forward. Could you devise a constant space
     * solution?
     * </pre>
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }

        prev = null;
        first = null;
        second = null;

        inorderTraversal(root);

        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }

    }
}
