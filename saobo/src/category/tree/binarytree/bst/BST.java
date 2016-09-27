package category.tree.binarytree.bst;

import org.junit.Assert;
import org.junit.Test;

import category.tree.binarytree.properties.TreeNode;

public class BST {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(30);

        System.out.println(new BST().findClosest(root, 5).val);

    }

    /**
     * [Google onsite] Given a BST and a target, return the node whose value is closest to the target.
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode findClosest(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        }

        if (val > root.val) {
            if (root.right == null) {
                return root;
            }

            TreeNode closedFromRight = findClosest(root.right, val);

            return Math.abs(closedFromRight.val - val) > Math.abs(root.val - val) ? root : closedFromRight;
        } else {
            if (root.left == null) {
                return root;
            }

            TreeNode closedFromLeft = findClosest(root.left, val);

            return Math.abs(closedFromLeft.val - val) > Math.abs(root.val - val) ? root : closedFromLeft;
        }
    }

    @Test
    public void testNotWithChildTarget() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(30);

        Assert.assertSame(root.right.left, new BST().findClosest(root, 16));
    }

    @Test
    public void testWithChildTarget() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(30);

        Assert.assertSame(root.left.right, new BST().findClosest(root, 8));
    }

    @Test
    public void testWithNullTree() {
        Assert.assertEquals(null, new BST().findClosest(null, 10));
    }

    @Test
    public void testWithRootTarget() {
        TreeNode root = new TreeNode(10);

        Assert.assertSame(root, new BST().findClosest(root, 10));
    }
}
