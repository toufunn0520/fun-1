package category.search.backtracking;

import java.util.ArrayList;
import java.util.List;

class TreeNode {

    TreeNode left;

    TreeNode right;

    int val;

    TreeNode(int x) {
        val = x;

    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

}

public class UniqueBST2 {

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
     *
     * </pre>
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return generateHelper(1, n);
    }

}
