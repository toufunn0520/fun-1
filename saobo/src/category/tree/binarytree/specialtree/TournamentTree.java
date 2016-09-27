package category.tree.binarytree.specialtree;

import category.tree.binarytree.properties.TreeNode;

/**
 * Tournament tree is a form of min (max) heap which is a complete binary tree. Every external node represents a player
 * and internal node represents winner. In a tournament tree every internal node contains winner and every leaf node
 * contains one player.
 *
 * <pre>
 *                 2
 *               /  \
 *             2     7
 *           /  \   / \
 *          5    2 8   7
 * </pre>
 */
public class TournamentTree {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * Assume all value in the tree is positive.
     * 
     * @param root
     * @return
     */
    public int findSecondMin(TreeNode root) {
        if ((root == null) || (root.left == null && root.right == null)) {
            return -1;
        }

        int left = -1;
        int right = -1;

        if (root.left.val == root.val) {
            left = findSecondMin(root.left);
        } else {
            left = root.left.val;
        }

        if (root.right.val == root.val) {
            right = findSecondMin(root.right);
        } else {
            right = root.right.val;
        }

        if (left == -1 || right == -1) {
            return left == -1 ? right : left;
        }

        return Math.min(left, right);
    }

}
