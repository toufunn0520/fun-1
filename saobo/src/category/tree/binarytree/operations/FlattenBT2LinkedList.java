package category.tree.binarytree.operations;

import category.tree.binarytree.properties.TreeNode;

public class FlattenBT2LinkedList {

    /**
     * [Leetcode 114] https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
     * 
     * <pre>
     * Given a binary tree, flatten it to a linked list in-place.
     *
     * For example,
     * Given
     *
     *          1
     *         / \
     *        2   5
     *       / \   \
     *      3   4   6
     * The flattened tree should look like:
     *    1
     *     \
     *      2
     *       \
     *        3
     *         \
     *          4
     *           \
     *            5
     *             \
     *              6
     *
     * </pre>
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                TreeNode current = root.left;
                while (current.right != null) {
                    current = current.right;
                }

                current.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }
}
