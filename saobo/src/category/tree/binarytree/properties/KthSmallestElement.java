package category.tree.binarytree.properties;

import java.util.LinkedList;
import java.util.Queue;

public class KthSmallestElement {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(8);
        root.right = new TreeNode(12);
        System.out.println(new KthSmallestElement().kthSmallest(root, 2));

    }

    private void inOrderTraversal(TreeNode current, Queue<TreeNode> processedQueue) {
        if (current == null) {
            return;
        }

        if (current.left != null) {
            inOrderTraversal(current.left, processedQueue);
        }

        processedQueue.offer(current);

        if (current.right != null) {
            inOrderTraversal(current.right, processedQueue);
        }
    }

    /**
     * [Leetcode 230] https://leetcode.com/problems/kth-smallest-element-in-a-bst/
     *
     * <pre>
     * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
     * </pre>
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        inOrderTraversal(root, queue);

        TreeNode current = null;
        for (int i = 0; i < k; i++) {
            current = queue.poll();
        }

        return current.val;
    }
}
