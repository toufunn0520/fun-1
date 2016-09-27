package category.tree.binarytree.properties;

public class SumRootToLeafNumbers {

    private int pathSumHelper(TreeNode node, int preValue) {
        int curValue = preValue * 10 + node.val;
        int sum = 0;
        if (node.left == null && node.right == null) {
            sum = curValue;
        }

        if (node.left != null) {
            sum += pathSumHelper(node.left, curValue);
        }

        if (node.right != null) {
            sum += pathSumHelper(node.right, curValue);
        }

        return sum;
    }

    /**
     * [Leetcode 129] https://leetcode.com/problems/sum-root-to-leaf-numbers/
     * 
     * <pre>
     * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
     *
     * An example is the root-to-leaf path 1->2->3 which represents the number 123.
     *
     * Find the total sum of all root-to-leaf numbers.
     *
     * For example,
     *
     *     1
     *    / \
     *   2   3
     * The root-to-leaf path 1->2 represents the number 12.
     * The root-to-leaf path 1->3 represents the number 13.
     *
     * Return the sum = 12 + 13 = 25.
     * </pre>
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = 0;
        if (root.left == null && root.right == null) {
            sum = root.val;
        }

        if (root.left != null) {
            sum += pathSumHelper(root.left, root.val);
        }
        if (root.right != null) {
            sum += pathSumHelper(root.right, root.val);
        }

        return sum;
    }
}
