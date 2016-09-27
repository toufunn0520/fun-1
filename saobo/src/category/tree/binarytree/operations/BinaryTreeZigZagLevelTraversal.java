package category.tree.binarytree.operations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import category.tree.binarytree.properties.TreeNode;

public class BinaryTreeZigZagLevelTraversal {

    /**
     * [Leetcode 103] https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
     * 
     * <pre>
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
     *
     * For example:
     * Given binary tree {3,9,20,#,#,15,7},
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its zigzag level order traversal as:
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     * </pre>
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode current = root;
        queue.offer(current);
        boolean toggle = true;
        int currentCount = 0;
        while (!queue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<Integer>();
            currentCount = queue.size();

            for (int i = 0; i < currentCount; i++) {
                current = queue.poll();

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }

                if (toggle) {
                    currentLevel.add(current.val);
                } else {
                    currentLevel.add(0, current.val);
                }
            }

            toggle = !toggle;
            result.add(currentLevel);
        }

        return result;
    }
}
