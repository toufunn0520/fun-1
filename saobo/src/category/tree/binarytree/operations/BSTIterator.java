package category.tree.binarytree.operations;

import java.util.Stack;

import category.tree.binarytree.properties.TreeNode;

/**
 * [Leetcode 173] https://leetcode.com/problems/binary-search-tree-iterator/
 * 
 * <pre>
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * </pre>
 *
 * @author boyi
 */
public class BSTIterator {

    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        TreeNode current = root;

        pushAllLeftChildren(current, stack);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode next = stack.pop();
        TreeNode current = next.right;
        pushAllLeftChildren(current, stack);

        return next.val;
    }

    private void pushAllLeftChildren(TreeNode current, Stack<TreeNode> stack) {
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
    }
}
