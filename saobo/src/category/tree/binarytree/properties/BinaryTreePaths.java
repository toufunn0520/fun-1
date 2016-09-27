package category.tree.binarytree.properties;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {

    /**
     * <pre>
     * Given a binary tree, return all root-to-leaf paths.
     * 
     * For example, given the following binary tree:
     * 
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     * All root-to-leaf paths are:
     * 
     * ["1->2->5", "1->3"]
     * </pre>
     * 
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> results = new ArrayList<String>();

        if (root == null) {
            return results;
        }

        if (root.left == null && root.right == null) {
            results.add(String.valueOf(root.val));
            return results;
        }

        if (root.left != null) {
            pathHelper(root.left, String.valueOf(root.val), results);
        }
        if (root.right != null) {
            pathHelper(root.right, String.valueOf(root.val), results);
        }

        return results;
    }

    private void pathHelper(TreeNode node, String path, List<String> results) {
        if (node.left == null && node.right == null) {
            results.add(path + "->" + node.val);
            return;
        }

        if (node.left != null) {
            pathHelper(node.left, path + "->" + node.val, results);
        }

        if (node.right != null) {
            pathHelper(node.right, path + "->" + node.val, results);
        }
    }
}
