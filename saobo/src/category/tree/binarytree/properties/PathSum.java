package category.tree.binarytree.properties;

import java.util.ArrayList;
import java.util.List;

public class PathSum {

    private class SumType {

        int maxSum;

        int singleSum;

        public SumType(int singleSum, int maxSum) {
            this.singleSum = singleSum;
            this.maxSum = maxSum;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        List<List<Integer>> results = new PathSum().pathSum(root, 22);

        for (List<Integer> result : results) {
            for (int i : result) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }

    /**
     * [Leetcode 112] https://leetcode.com/problems/path-sum/
     *
     * <pre>
     * Given a binary tree and a sum, determine if the tree has a
     * root-to-leaf path such that adding up all the values along the path
     * equals the given sum.
     * 
     * For example:
     * Given the below binary tree and sum = 22,
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \      \
     *         7    2      1
     * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
     * </pre>
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                return true;
            } else {
                return false;
            }
        } else {
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
    }

    /**
     * [Leetcode 124] https://leetcode.com/problems/binary-tree-maximum-path-sum/
     * 
     * <pre>
     * Given a binary tree, find the maximum path sum.
     * The path may start and end at any node in the tree.
     * For example:
     * Given the below binary tree,
     * 
     *        1
     *       / \
     *      2   3
     * Return 6.
     * </pre>
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        return maxPathSumHelper(root).maxSum;
    }

    private SumType maxPathSumHelper(TreeNode root) {
        if (root == null) {
            return new SumType(0, Integer.MIN_VALUE);
        }

        SumType leftSum = maxPathSumHelper(root.left);
        SumType rightSum = maxPathSumHelper(root.right);

        int leftSingle = leftSum.singleSum;
        int rightSingle = rightSum.singleSum;

        int singleSum = Math.max(leftSingle, rightSingle) + root.val;
        singleSum = Math.max(0, singleSum);

        int subMaxSum = Math.max(leftSum.maxSum, rightSum.maxSum);
        int maxSum = Math.max(subMaxSum, leftSingle + root.val + rightSingle);

        return new SumType(singleSum, maxSum);
    }

    /**
     * [Leetcode 113] https://leetcode.com/problems/path-sum-ii/
     *
     * <pre>
     * Given a binary tree and a sum, find all root-to-leaf paths where each
     * path's sum equals the given sum. For example: Given the below binary tree
     * and sum = 22,
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     * return
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     * </pre>
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        List<Integer> result = new ArrayList<Integer>();
        pathSumHelper(root, sum, result, results);

        return results;
    }

    private void pathSumHelper(TreeNode root, int sum, List<Integer> result, List<List<Integer>> results) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null && root.val == sum) {
            result.add(root.val);
            results.add(new ArrayList<Integer>(result));
            result.remove(result.size() - 1);
            return;
        }

        result.add(root.val);
        pathSumHelper(root.left, sum - root.val, result, results);
        pathSumHelper(root.right, sum - root.val, result, results);
        result.remove(result.size() - 1);
    }
}
