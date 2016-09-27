package category.tree.binarytree.operations;

import java.util.LinkedList;
import java.util.Queue;

import category.tree.binarytree.properties.TreeNode;

/**
 * [Leetcode 297] https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * <pre>
 * Serialization is the process of
 * converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 * </pre>
 */
public class SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(20);
        node.left = new TreeNode(5);
        node.right = new TreeNode(50);
        node.left.left = new TreeNode(1);
        node.right.right = new TreeNode(30);
        node.right.right.right = new TreeNode(100);

        String deserialized = new SerializeAndDeserializeBinaryTree().serialize(node);
        System.out.println(deserialized);
        System.out.println(new SerializeAndDeserializeBinaryTree().deserialize(deserialized));

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("#")) {
            return null;
        }

        int i = 0;
        String[] vals = data.split(",");
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(Integer.parseInt(vals[i++]));
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (vals[i].equals("#")) {
                current.left = null;
            } else {
                current.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.offer(current.left);
            }
            i++;

            if (vals[i].equals("#")) {
                current.right = null;
            } else {
                current.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        sb.append(root.val);

        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    sb.append("," + current.left.val);
                    queue.offer(current.left);
                } else {
                    sb.append(",#");
                }
                if (current.right != null) {
                    sb.append("," + current.right.val);
                    queue.offer(current.right);
                } else {
                    sb.append(",#");
                }
            }
        }

        return sb.toString();
    }
}
