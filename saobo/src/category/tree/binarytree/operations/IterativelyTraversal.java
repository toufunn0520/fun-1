package category.tree.binarytree.operations;

import interview.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import category.tree.binarytree.properties.TreeNode;

public class IterativelyTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(23);

        ListUtils.listPrint(new IterativelyTraversal().postorderTraversal(root));

    }

    /**
     * [Leetcode 94] https://leetcode.com/problems/binary-tree-inorder-traversal/
     *
     * <pre>
     * Given a binary tree, return the inorder traversal of its nodes' values.
     * 
     * For example:
     * Given binary tree {1,#,2,3},
     *    1
     *     \
     *      2
     *     /
     *    3
     * return [1,3,2].
     * </pre>
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> processStack = new Stack<TreeNode>();

        TreeNode current = root;
        while (!processStack.isEmpty() || current != null) {
            if (current != null) {
                processStack.push(current);
                current = current.left;
            } else {
                current = processStack.pop();
                result.add(current.val);
                current = current.right;
            }
        }

        return result;
    }

    /**
     * [Leetcode ] https://leetcode.com/problems/binary-tree-postorder-traversal/
     * 
     * <pre>
     * Given a binary tree, return the postorder traversal of its nodes' values.
     * 
     * For example:
     * Given binary tree {1,#,2,3},
     *    1
     *     \
     *      2
     *     /
     *    3
     * return [3,2,1].
     * </pre>
     * 
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        if (root == null) {
            return result;
        }

        Stack<TreeNode> processStack = new Stack<TreeNode>();
        processStack.push(root);

        TreeNode prev = null;
        while (!processStack.isEmpty()) {
            TreeNode current = processStack.peek();
            if (prev == null || prev.left == current || prev.right == current) {
                if (current.left != null) {
                    processStack.push(current.left);
                } else if (current.right != null) {
                    processStack.push(current.right);
                }
            } else if (prev == current.left) {
                if (current.right != null) {
                    processStack.push(current.right);
                }
            } else {
                result.add(current.val);
                processStack.pop();
            }

            prev = current;
        }
        return result;
    }

    /**
     * [Leetcode 144] https://leetcode.com/problems/binary-tree-preorder-traversal/
     *
     * <pre>
     * Given a binary tree, return the preorder traversal of its nodes' values.
     * </pre>
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            result.add(current.val);

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return result;
    }
}
