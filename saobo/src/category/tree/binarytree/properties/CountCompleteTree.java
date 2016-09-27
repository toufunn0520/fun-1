package category.tree.binarytree.properties;

public class CountCompleteTree {

    public static void main(String[] args) {
        System.out.println(2 << -1);

    }

    /**
     * [Leetcode 222] https://leetcode.com/problems/count-complete-tree-nodes/
     *
     * <pre>
     * Given a complete binary tree, count the number of nodes.
     * Definition of a complete binary tree from Wikipedia:
     * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as
     * far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
     * </pre>
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = 0, rightDepth = 0;
        TreeNode left = root, right = root;
        while (left != null) {
            leftDepth++;
            left = left.left;
        }

        while (right != null) {
            rightDepth++;
            right = right.right;
        }

        if (leftDepth == rightDepth) {
            return (2 << (leftDepth - 1)) - 1;
        }

        return countNodes(root.left) + countNodes(root.right) + 1;
    }

}
