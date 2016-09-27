package category.tree.binarytree.operations;

import category.tree.binarytree.properties.TreeNode;

public class ConstructBinaryTrees {

    public static void main(String[] args) {
        int[] post = { 9, 12, 10, 5 };
        int[] pre = { 5, 10, 9, 12 };

        TreeNode root = new ConstructBinaryTrees().buildFullBinaryTreeFromPostorderAndPreorder(post, pre);

        root.print();
    }

    /**
     * This method can only return one possible of full BT. i.e if there is no left but has right child, it can only
     * return the BT with left child but no right child.
     * 
     * @param postorder
     * @param preorder
     * @return
     */
    public TreeNode buildFullBinaryTreeFromPostorderAndPreorder(int[] postorder, int[] preorder) {
        if (postorder == null || postorder.length == 0 || preorder == null || preorder.length == 0
                || preorder.length != postorder.length) {
            return null;
        }

        int count = postorder.length;
        return buildTreeFromPostorderAndPreorderHelper(postorder, 0, count - 1, preorder, 0, count - 1);
    }

    private TreeNode buildTreeFromPostorderAndPreorderHelper(int[] postorder, int postStartIndex, int postEndIndex,
            int[] preorder, int preStartIndex, int preEndIndex) {

        if (preStartIndex > preEndIndex) {
            return null;
        }

        TreeNode current = new TreeNode(preorder[preStartIndex]);

        if (preStartIndex == preEndIndex) {
            return current;
        }

        int leftChildIndexOfPostorder = getIndex(postorder, postStartIndex, postEndIndex, preorder[preStartIndex + 1]);
        int leftCount = leftChildIndexOfPostorder - postStartIndex + 1;

        current.left = buildTreeFromPostorderAndPreorderHelper(postorder, postStartIndex, leftChildIndexOfPostorder,
                preorder, preStartIndex + 1, preStartIndex + leftCount);
        current.right = buildTreeFromPostorderAndPreorderHelper(postorder, postStartIndex + leftCount,
                postEndIndex - 1, preorder, preStartIndex + leftCount + 1, preEndIndex);

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

}
