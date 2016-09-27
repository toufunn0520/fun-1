package category.tree.binarytree.properties;

import org.junit.Assert;
import org.junit.Test;

public class CommonAncestor {

    private static Node getCommonAncestorWithParentPointer(Node node1, Node node2, int differenceOfHeight) {
        while (differenceOfHeight-- > 0) {
            node1 = node1.parent;
        }

        while (node1 != null && node2 != null) {
            if (node1 == node2) {
                return node1;
            } else {
                node1 = node1.parent;
                node2 = node2.parent;
            }
        }

        return null;
    }

    /**
     * Get deepest common ancestor with parent pointer.
     *
     * @param node1
     * @param node2
     * @return
     */
    public static Node getDeepestCommonAncestor(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return null;
        }

        int height1 = getHeight(node1);
        int height2 = getHeight(node2);

        if (height1 > height2) {
            return getCommonAncestorWithParentPointer(node1, node2, height1 - height2);
        } else {
            return getCommonAncestorWithParentPointer(node2, node1, height2 - height1);
        }
    }

    private static int getHeight(Node node) {
        int height = 0;

        while (node != null) {
            node = node.parent;
            height++;
        }

        return height;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);

        root.left = new TreeNode(2);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        System.out
        .println(new CommonAncestor().lowestCommonAncestorOfBT(root, root.left.left, root.left.right.left).val);
    }

    /**
     * [Leetcode 236] https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
     *
     * <pre>
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree. According to the
     * definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest
     * node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
     *             _______3______
     *            /               \
     *        ___5__            ___1__
     *       /      \          /      \
     *      6       _2         0       8
     *             /  \
     *             7   4
     *
     * </pre>
     *
     * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is
     * 5, since a node can be a descendant of itself according to the LCA definition.
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorOfBT(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestorOfBT(root.left, p, q);
        TreeNode right = lowestCommonAncestorOfBT(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else {
            return left == null ? right : left;
        }
    }

    @Test
    public void testWithDifferentCases() {
        Node[] nodes = new Node[10];
        for (int i = 0; i < 10; i++) {
            nodes[i] = new Node();
        }

        for (int i = 5; i > 0; i--) {
            nodes[i].parent = nodes[i - 1];
        }

        for (int i = 9; i > 6; i--) {
            nodes[i].parent = nodes[i - 1];
        }
        nodes[6].parent = nodes[0];

        for (int i = 1; i <= 5; i++) {
            for (int j = 6; j <= 9; j++) {
                Assert.assertEquals(nodes[0], getDeepestCommonAncestor(nodes[i], nodes[j]));
            }
        }

        for (int i = 0; i <= 5; i++) {
            for (int j = i; j <= 5; j++) {
                Assert.assertEquals(nodes[i], getDeepestCommonAncestor(nodes[i], nodes[j]));
            }
        }
    }
}

class Node {

    Node parent;
}
