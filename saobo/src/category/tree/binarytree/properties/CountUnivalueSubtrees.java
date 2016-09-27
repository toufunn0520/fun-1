package category.tree.binarytree.properties;

import org.junit.Assert;
import org.junit.Test;

public class CountUnivalueSubtrees {

    /**
     * Given a binary tree, count the number of uni-value subtrees. A Uni-value subtree means all nodes of the subtree
     * have the same value.
     *
     * @param root
     * @return
     */
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] count = { 0 };

        univalueHelper(root, count);

        return count[0];
    }

    private boolean univalueHelper(TreeNode root, int[] count) {
        if (root.left == null && root.right == null) {
            count[0]++;
            return true;
        }

        if (root.left == null && root.right != null) {
            if (univalueHelper(root.right, count) && root.val == root.right.val) {
                count[0]++;
                return true;
            } else {
                return false;
            }
        } else if (root.right == null && root.left != null) {
            if (univalueHelper(root.left, count) && root.val == root.left.val) {
                count[0]++;
                return true;
            } else {
                return false;
            }
        } else {
            boolean left = univalueHelper(root.left, count);
            boolean right = univalueHelper(root.right, count);

            if (left && right && root.left.val == root.val && root.right.val == root.val) {
                count[0]++;
                return true;
            } else {
                return false;
            }
        }
    }

    @Test
    public void testWhenWithOnlyDifferentNodes() {
        TreeNode onlyRoot = new TreeNode(1);

        TreeNode allUnique = new TreeNode(1);
        allUnique.left = new TreeNode(1);
        allUnique.right = new TreeNode(1);

        TreeNode completeTree = new TreeNode(5);
        completeTree.left = new TreeNode(1);
        completeTree.right = new TreeNode(3);
        completeTree.left.left = new TreeNode(5);
        completeTree.left.right = new TreeNode(5);
        completeTree.right.right = new TreeNode(3);

        CountUnivalueSubtrees counter = new CountUnivalueSubtrees();

        Assert.assertEquals(1, counter.countUnivalSubtrees(onlyRoot));
        Assert.assertEquals(3, counter.countUnivalSubtrees(allUnique));
        Assert.assertEquals(4, counter.countUnivalSubtrees(completeTree));
    }
}
