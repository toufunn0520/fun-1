package category.tree.binarytree.properties;

public class InorderSuccessor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        TreeNode current = new InorderSuccessor().inorderSuccessorBST(root, root.left);
        System.out.println(current.val);

    }

    /**
     * [Leetcode 285] https://leetcode.com/problems/inorder-successor-in-bst/
     * 
     * <pre>
     * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
     * </pre>
     * 
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessorBST(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }

        if (p.right != null) {
            TreeNode current = p.right;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        } else {
            TreeNode current = root;
            TreeNode previous = null;
            while (current != null) {
                if (current.val < p.val) {
                    current = current.right;
                } else if (current.val > p.val) {
                    previous = current;
                    current = current.left;
                } else {
                    return previous;
                }
            }
            return null;
        }
    }

}
