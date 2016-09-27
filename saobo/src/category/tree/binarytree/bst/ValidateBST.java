package category.tree.binarytree.bst;

import category.tree.binarytree.properties.TreeNode;

public class ValidateBST {

    private boolean firstNode = true;

    private int lastVal;

    /**
     * [Leetcode 98] https://leetcode.com/problems/validate-binary-search-tree/
     * 
     * <pre>
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     * 
     * Assume a BST is defined as follows:
     * 
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     * </pre>
     * 
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (!firstNode && lastVal >= root.val) {
            return false;
        }
        firstNode = false;
        lastVal = root.val;
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }
}
