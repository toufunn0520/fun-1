package category.tree.binarytree.specialtree;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

class RTENode {

    RTENode left;

    int length;

    RTENode right;

    int startIndex;

    int value;

    public RTENode(int value, int startIndex, int length) {
        this.value = value;
        this.startIndex = startIndex;
        this.length = length;
    }
}

public class ValidRLETree {

    /**
     * [Tableau] Determine if the Run-time Encoding tree is valid. A valid tree should have continuous coding values.
     * Basic idea is to store Run-time Encoding code in BST, each node has a start index and length of the value. I.e
     * 111122 encoded as two node: 1: value 1, length 4, startIndex 0, node2: value 2, length 2, startIndex 5.
     *
     * @param root
     * @return
     */
    public static boolean isValid(RTENode root) {
        if (root == null) {
            return true;
        }

        RTENode current = root;
        Stack<RTENode> stack = new Stack<RTENode>();

        RTENode prev = null;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                if (current.length <= 0) {
                    return false;
                }

                if (prev != null) {
                    if (prev.value >= current.value || prev.startIndex + prev.length != current.startIndex) {
                        return false;
                    }
                } else if (current.startIndex != 0) {
                    return false;
                }

                prev = current;
                current = current.right;
            }
        }

        return true;
    }

    @Test
    public void testWithFullTree() {
        RTENode root = new RTENode(10, 3, 3);
        root.left = new RTENode(5, 0, 3);
        root.right = new RTENode(15, 6, 9);

        Assert.assertEquals(true, isValid(root));
    }

    @Test
    public void testWithInvalidLength() {
        RTENode root = new RTENode(10, -3, 8);
        root.left = new RTENode(5, 0, -3);
        root.right = new RTENode(15, 5, 9);

        Assert.assertEquals(false, isValid(root));
    }

    @Test
    public void testWithInvalidRange() {
        RTENode root = new RTENode(10, 3, 3);
        root.left = new RTENode(5, 0, 4);
        root.right = new RTENode(15, 6, 9);

        Assert.assertEquals(false, isValid(root));
    }

    @Test
    public void testWithInvalidStartIndex() {
        RTENode root = new RTENode(10, 3, 3);
        root.left = new RTENode(5, -1, 4);
        root.right = new RTENode(15, 6, 9);

        Assert.assertEquals(false, isValid(root));
    }

    @Test
    public void testWithInvalidValue() {
        RTENode root = new RTENode(10, 3, 3);
        root.left = new RTENode(10, 0, 3);
        root.right = new RTENode(15, 6, 9);

        Assert.assertEquals(false, isValid(root));
    }
}
