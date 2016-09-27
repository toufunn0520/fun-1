package category.tree.binarytree.properties;

public class DepthOfBinaryTree {

    /**
     * [Leetcode 104] https://leetcode.com/problems/maximum-depth-of-binary-tree/
     *
     * <pre>
     * Given a binary tree, find its maximum depth.
     *
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     * </pre>
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * [Leetcode 111] https://leetcode.com/problems/minimum-depth-of-binary-tree/
     * 
     * <pre>
     * Given a binary tree, find its minimum depth.
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     * </pre>
     * 
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if (left == 0) {
            return 1 + right;
        }

        if (right == 0) {
            return 1 + left;
        }

        return 1 + Math.min(left, right);
    }
}
