package category.tree.binarytree.operations;

public class PopulatingNextRight {

    /**
     * [Leetcode 116] https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
     *
     * <pre>
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set
     * to NULL. Initially, all next pointers are set to NULL.
     * 
     * Note:
     * 
     * You may only use constant extra space.
     * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
     * For example,
     * Given the following perfect binary tree,
     *          1
     *        /  \
     *       2    3
     *      / \  / \
     *     4  5  6  7
     * After calling your function, the tree should look like:
     *          1 -> NULL
     *        /  \
     *       2 -> 3 -> NULL
     *      / \  / \
     *     4->5->6->7 -> NULL
     * </pre>
     *
     * @param root
     */
    public void connect(TreeLinkNode root) {
        TreeLinkNode prev = root;
        TreeLinkNode next = null;

        while (prev != null) {
            next = prev.left;

            while (prev != null && prev.left != null) {
                prev.left.next = prev.right;
                if (prev.next != null) {
                    prev.right.next = prev.next.left;
                }
                prev = prev.next;
            }
            prev = next;
        }
    }

    /**
     * [Leetcode 117] https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
     * 
     * <pre>
     * Follow up for problem "Populating Next Right Pointers in Each Node".
     *
     * What if the given tree could be any binary tree? Would your previous solution still work?
     *
     * Note:
     *
     * You may only use constant extra space.
     * For example,
     * Given the following binary tree,
     *          1
     *        /  \
     *       2    3
     *      / \    \
     *     4   5    7
     * After calling your function, the tree should look like:
     *          1 -> NULL
     *        /  \
     *       2 -> 3 -> NULL
     *      / \    \
     *     4-> 5 -> 7 -> NULL
     * </pre>
     *
     * @param root
     */
    public void connect2(TreeLinkNode root) {
        TreeLinkNode current = root;
        TreeLinkNode prevNextLevel = null;
        TreeLinkNode nextHead = null;

        while (current != null) {
            prevNextLevel = null;
            nextHead = null;

            while (current != null) {
                if (current.left != null) {
                    if (prevNextLevel == null) {
                        nextHead = current.left;
                        prevNextLevel = current.left;
                    } else {
                        prevNextLevel.next = current.left;
                        prevNextLevel = prevNextLevel.next;
                    }
                }

                if (current.right != null) {
                    if (prevNextLevel == null) {
                        nextHead = current.right;
                        prevNextLevel = current.right;
                    } else {
                        prevNextLevel.next = current.right;
                        prevNextLevel = prevNextLevel.next;
                    }
                }
                current = current.next;
            }
            current = nextHead;
        }
    }
}

class TreeLinkNode {

    TreeLinkNode left;

    TreeLinkNode next;

    TreeLinkNode right;
}
