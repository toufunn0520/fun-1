package category.tree.binarytree.bst;

import category.tree.binarytree.properties.TreeNode;

public class ClosestBSTValue {

    /**
     * [Leetcode 270] https://leetcode.com/problems/closest-binary-search-tree-value/
     * 
     * <pre>
     * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
     *
     * Note:
     * Given target value is a floating point.
     * You are guaranteed to have only one unique value in the BST that is closest to the target.
     * </pre>
     *
     * @param focusNode
     * @param target
     * @return
     */
    public int closestValue(TreeNode focusNode, double target) {
        int result = focusNode.val;

        while (focusNode != null) {
            focusNode = (target < focusNode.val) ? focusNode.left : focusNode.right;

            if (focusNode != null) {
                double diff1 = Math.abs(target - focusNode.val);
                double diff2 = Math.abs(target - result);

                result = diff1 < diff2 ? focusNode.val : result;
            }
        }

        return result;
    }
}
