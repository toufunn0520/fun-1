package category.tree.binarytree.properties;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {

    /**
     * [Leetcode 199] https://leetcode.com/problems/binary-tree-right-side-view/
     * 
     * <pre>
     * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
     *
     * For example:
     * Given the following binary tree,
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     * You should return [1, 3, 4].
     * </pre>
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightSideValues = new LinkedList<Integer>();

        if (root == null) {
            return rightSideValues;
        }

        int currentLevelCount = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        TreeNode current = null;

        while (!queue.isEmpty()) {
            currentLevelCount = queue.size();

            while (currentLevelCount-- != 0) {
                current = queue.poll();

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            rightSideValues.add(current.val);
        }
        return rightSideValues;
    }

}
