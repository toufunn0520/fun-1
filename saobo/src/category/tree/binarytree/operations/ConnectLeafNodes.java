package category.tree.binarytree.operations;

import category.tree.binarytree.properties.TreeNode;

public class ConnectLeafNodes {

    private static ListNode concatenate(ListNode node1, ListNode node2) {
        if (node1 == null) {
            return node2;
        }

        ListNode current = node1;
        while (current.next != null) {
            current = current.next;
        }

        current.next = node2;
        return node1;
    }

    /**
     * [Microsoft phone]
     * 
     * @param root
     * @return
     */
    public static ListNode connectLeaf(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            return new ListNode(root.val);
        } else {

            ListNode left = connectLeaf(root.left);
            ListNode right = connectLeaf(root.right);

            return concatenate(left, right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(10);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(5);

        ListNode head = connectLeaf(root);

        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }

    }

}
