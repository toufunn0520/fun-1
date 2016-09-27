package category.tree.binarytree.properties;

public class IsSameTree {

    /**
     * [Leetcode 100] https://leetcode.com/problems/same-tree/
     *
     * <pre>
     * Given two binary trees, write a function to check if they
     * are equal or not. Two binary trees are considered equal if they are structurally identical and the nodes have the
     * same value.
     * </pre>
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;

        return isSameTree(p.left, q.left) && (p.val == q.val) && isSameTree(p.right, q.right);
    }
}
