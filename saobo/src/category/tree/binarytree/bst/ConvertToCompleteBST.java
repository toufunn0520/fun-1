package category.tree.binarytree.bst;

import org.junit.Assert;
import org.junit.Test;

import category.tree.binarytree.properties.TreeNode;

public class ConvertToCompleteBST {

    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

        TreeNode root = new ConvertToCompleteBST().sortedArrayToBST(array);
        root.print();
    }

    private void assertTreeEquals(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return;
        }

        if (root1 == null && root2 == null) {
            Assert.fail();
        }

        Assert.assertEquals(root1.val, root2.val);
        assertTreeEquals(root1.left, root2.left);
        assertTreeEquals(root1.right, root2.right);
    }

    private TreeNode getCompletedBST(int[] array, int start, int end) {
        if (start > end) {
            return null;
        }

        int currentIndex = getRootIndex(start, end - start + 1);

        TreeNode current = new TreeNode(array[currentIndex]);
        current.left = getCompletedBST(array, start, currentIndex - 1);
        current.right = getCompletedBST(array, currentIndex + 1, end);

        return current;
    }

    private int getHeight(int count) {
        return (int) (Math.ceil(Math.log10(count + 1) / Math.log10(2)));
    }

    public int getRootIndex(int startIndex, int count) {
        if (count == 1) {
            return startIndex;
        }

        int height = getHeight(count);

        if (count > Math.pow(2, height - 2) * 3 - 1) {
            // in this case: left sub tree is a complete tree
            return startIndex + (int) Math.pow(2, height - 1) - 1;
        } else {
            // in this case: left sub tree is not a compelte tree
            return startIndex + count - (int) Math.pow(2, height - 2);
        }
    }

    public TreeNode sortedArrayToBST(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        return getCompletedBST(array, 0, array.length - 1);
    }

    @Test
    public void testWithFiveNodes() {
        int[] array = { 1, 2, 3, 4, 5 };

        ConvertToCompleteBST converter = new ConvertToCompleteBST();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(5);

        assertTreeEquals(root, converter.sortedArrayToBST(array));
    }

    @Test
    public void testWithFourNodes() {
        int[] array = { 1, 2, 3, 4 };

        ConvertToCompleteBST converter = new ConvertToCompleteBST();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.right = new TreeNode(4);

        assertTreeEquals(root, converter.sortedArrayToBST(array));
    }

    @Test
    public void testWithOneNode() {
        int[] oneNode = { 1 };

        ConvertToCompleteBST converter = new ConvertToCompleteBST();
        assertTreeEquals(new TreeNode(1), converter.sortedArrayToBST(oneNode));
    }

    @Test
    public void testWithThreeNodes() {
        int[] array = { 1, 2, 3 };

        ConvertToCompleteBST converter = new ConvertToCompleteBST();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        assertTreeEquals(root, converter.sortedArrayToBST(array));
    }

    @Test
    public void testWithTwoNodes() {
        int[] array = { 1, 2 };

        ConvertToCompleteBST converter = new ConvertToCompleteBST();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);

        assertTreeEquals(root, converter.sortedArrayToBST(array));
    }
}
