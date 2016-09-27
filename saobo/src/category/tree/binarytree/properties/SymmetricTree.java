package category.tree.binarytree.properties;

public class SymmetricTree {

    private boolean isMirrorSame(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }

        return isMirrorSame(root1.left, root2.right) && isMirrorSame(root1.right, root2.left);
    }

    /**
     * [Leetcode 101] https://leetcode.com/problems/symmetric-tree/
     * 
     * <pre>
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     *
     * For example, this binary tree is symmetric:
     *
     * 1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     * But the following is not:
     * 1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     * Note:
     * Bonus points if you could solve it both recursively and iteratively.
     * </pre>
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isMirrorSame(root.left, root.right);
    }
}
