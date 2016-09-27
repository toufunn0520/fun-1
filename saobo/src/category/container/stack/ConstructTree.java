package category.container.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ConstructTree {

    private static class TreeNode {

        List<TreeNode> children;

        char val;

        public TreeNode(char val) {
            this.val = val;
            children = new ArrayList<TreeNode>();
        }

        public void print() {
            TreeNode current = this;

            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.add(this);

            while (!queue.isEmpty()) {
                int count = queue.size();
                for (int i = 0; i < count; i++) {
                    current = queue.poll();
                    for (TreeNode child : current.children) {
                        queue.offer(child);
                    }
                    System.out.print(current.val + " ");
                }
                System.out.println();
            }
        }

    }

    public static void main(String[] args) {
        TreeNode result = new ConstructTree().convertFromExpression("(A(B(C)(D))(E)(F))");
        result.print();

    }

    /**
     * [Google] Construct Tree from expression including parentheses. For example:
     *
     * <pre>
     * input: (A(B(C)(D))(E)(F))
     *
     * output:
     *              A
     *           /    \   \
     *          B     E    F
     *         / \
     *        C   D
     * </pre>
     *
     * @param s
     * @return
     */
    public TreeNode convertFromExpression(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        char[] chars = s.toCharArray();
        TreeNode root = new TreeNode(chars[1]);
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root);

        for (int i = 2; i < chars.length; i++) {
            if (chars[i] == '(') {
                TreeNode node = new TreeNode(chars[++i]);
                stack.peek().children.add(node);
                stack.push(node);
            } else if (chars[i] == ')') {
                stack.pop();
            }
        }

        return root;
    }

}
