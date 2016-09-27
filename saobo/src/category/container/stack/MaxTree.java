package category.container.stack;

import java.util.Stack;

public class MaxTree {

    static private class TreeNode {

        TreeNode left, right;

        int val;

        TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 8, 10, 15, 8, 4, 6 };

        TreeNode current = maxTree(nums);

        System.out.println(current.val);
    }

    /**
     * <pre>
     * Given an integer array with no duplicates. A max tree building on this array is defined as follow:
     * 
     * The root is the maximum number in the array
     * The left subtree and right subtree are the max trees of the subarray divided by the root number.
     * Construct the max tree by the given array.
     * </pre>
     *
     * @param A
     * @return
     */
    public static TreeNode maxTree(int[] A) {
        Stack<TreeNode> stack = new Stack<TreeNode>();

        for (int i = 0; i <= A.length; i++) {
            TreeNode right = i == A.length ? new TreeNode(Integer.MAX_VALUE) : new TreeNode(A[i]);
            while (!stack.isEmpty()) {
                if (right.val > stack.peek().val) {
                    TreeNode currentNode = stack.pop();

                    if (stack.isEmpty()) {
                        right.left = currentNode;
                    } else {
                        TreeNode left = stack.peek();
                        if (left.val > right.val) {
                            right.left = currentNode;
                        } else {
                            left.right = currentNode;
                        }
                    }
                } else {
                    break;
                }
            }
            stack.push(right);
        }
        return stack.peek().left;
    }

}
