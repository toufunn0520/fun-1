package category.tree.binarytree.operations;

import category.tree.binarytree.properties.TreeNode;

public class ConstructBTfromTraversals {

    public static void main(String[] args) {
        int[] inorder = { 1, 3, 4, 5, 9, 10, 12 };
        int[] post = { 1, 4, 3, 9, 12, 10, 5 };

        TreeNode root = new ConstructBTfromTraversals().buildTree2(inorder, post);

        root.print();
    }

    /**
     * [Leetcode 105] https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     * 
     * <pre>
     * Given preorder and inorder traversal of a tree, construct the binary tree. Note: You may assume that duplicates
     * do not exist in the tree.
     * </pre>
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0
                || preorder.length != inorder.length) {
            return null;
        }

        int count = preorder.length;

        return myBuildTree(inorder, 0, count - 1, preorder, 0, count - 1);
    }

    /**
     * [Leetcode 106] https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
     * 
     * <pre>
     * Given inorder and postorder traversal of a tree, construct the binary tree. Note: You may assume that duplicates
     * do not exist in the tree.
     * </pre>
     *
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0
                || inorder.length != postorder.length) {
            return null;
        }

        int count = inorder.length;

        return buildTreeFromInorderPostOrder(inorder, 0, count - 1, postorder, 0, count - 1);
    }

    private TreeNode buildTreeFromInorderPostOrder(int[] inorder, int inStartIndex, int inEndIndex, int[] postorder,
            int postStartIndex, int postEndIndex) {

        if (inStartIndex > inEndIndex) {
            return null;
        }

        TreeNode current = new TreeNode(postorder[postEndIndex]);

        int inorderIndex = getIndex(inorder, inStartIndex, inEndIndex, postorder[postEndIndex]);
        // don't forget to minus inStartIndex!!!
        int leftCount = inorderIndex - inStartIndex;

        current.left = buildTreeFromInorderPostOrder(inorder, inStartIndex, inorderIndex - 1, postorder,
                postStartIndex, postStartIndex + leftCount - 1);
        current.right = buildTreeFromInorderPostOrder(inorder, inorderIndex + 1, inEndIndex, postorder, postStartIndex
                + leftCount, postEndIndex - 1);

        return current;
    }

    private int getIndex(int[] numbers, int startIndex, int endIndex, int target) {
        for (int i = startIndex; i <= endIndex; i++) {
            if (numbers[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private TreeNode myBuildTree(int[] inorder, int instart, int inend, int[] preorder, int prestart, int preend) {
        if (instart > inend) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[prestart]);
        int position = getIndex(inorder, instart, inend, preorder[prestart]);

        root.left = myBuildTree(inorder, instart, position - 1, preorder, prestart + 1, prestart + position - instart);
        root.right = myBuildTree(inorder, position + 1, inend, preorder, position - inend + preend + 1, preend);
        return root;
    }

}
