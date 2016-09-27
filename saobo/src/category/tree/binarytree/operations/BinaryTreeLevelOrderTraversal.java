package category.tree.binarytree.operations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import category.tree.binarytree.properties.TreeNode;

public class BinaryTreeLevelOrderTraversal {

    /**
     * [Leetcode 102] https://leetcode.com/problems/binary-tree-level-order-traversal/
     *
     * <pre>
     * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
     * 
     * For example:
     * Given binary tree {3,9,20,#,#,15,7},
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its level order traversal as:
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     *
     * </pre>
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int count = 1;
        List<Integer> currentLevel = new ArrayList<Integer>();
        while (count > 0) {
            TreeNode node = queue.poll();
            currentLevel.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            if (--count == 0) {
                result.add(new ArrayList<Integer>(currentLevel));
                currentLevel.clear();
                count = queue.size();
            }
        }

        return result;
    }

    /**
     * [Leetcode 107] https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
     * 
     * <pre>
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
     * 
     * For example:
     * Given binary tree {3,9,20,#,#,15,7},
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its bottom-up level order traversal as:
     * [
     *   [15,7],
     *   [9,20],
     *   [3]
     * ]
     * </pre>
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int count = 1;
        List<Integer> currentLevel = new ArrayList<Integer>();
        while (count > 0) {
            TreeNode node = queue.poll();
            currentLevel.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            if (--count == 0) {
                result.add(0, new LinkedList<Integer>(currentLevel));
                currentLevel.clear();
                count = queue.size();
            }
        }

        return result;
    }
}
