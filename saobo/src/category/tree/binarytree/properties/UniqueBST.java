package category.tree.binarytree.properties;

import java.util.ArrayList;
import java.util.List;

public class UniqueBST {

    public static void main(String[] args) {
        new UniqueBST().generateTrees(3);
    }

    private List<TreeNode> generateHelper(int start, int end) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        if (start > end) {
            list.add(null);
            return list;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = generateHelper(start, i - 1);
            List<TreeNode> rights = generateHelper(i + 1, end);

            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }

        return list;
    }

    /**
     * [Leetcode 95] https://leetcode.com/problems/unique-binary-search-trees-ii/
     * 
     * <pre>
     * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
     *
     * For example,
     * Given n = 3, your program should return all 5 unique BST's shown below.
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     * </pre>
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return generateHelper(1, n);
    }
}
