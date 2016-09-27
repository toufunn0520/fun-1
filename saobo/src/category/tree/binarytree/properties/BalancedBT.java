package category.tree.binarytree.properties;


public class BalancedBT {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * [Leetcode 110] https://leetcode.com/problems/balanced-binary-tree/
     * 
     * <pre>
     * Given a binary tree, determine if it is height-balanced. For this problem, a height-balanced binary tree is
     * defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     * </pre>
     * 
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }

    /**
     * Modification of max depth If current node is null, return 0 Compare left depth with right depth If the difference
     * is bigger than 1, set isBalance false Otherwise go on to the rest of the nodes
     */
    private int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        return Math.max(left, right) + 1;
    }

}
