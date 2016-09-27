package category.tree.binarytree.properties;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

class PrettyPrinter {

    private int indentSpace;

    private int level;

    private TreeNode root;

    public PrettyPrinter(TreeNode root, int level, int indentSpace) {
        this.root = root;
        this.level = level;
        this.indentSpace = indentSpace;
    }

    public void prettyPrint() {
        int h = root.getHeight();
        int nodesInThisLevel = 1;

        int branchLen = 2 * ((int) Math.pow(2.0, h) - 1) - (3 - level) * (int) Math.pow(2.0, h - 1);
        int nodeSpaceLen = 2 + (level + 1) * (int) Math.pow(2.0, h);
        int startLen = branchLen + (3 - level) + indentSpace;

        Deque<TreeNode> nodesQueue = new LinkedList<TreeNode>();
        nodesQueue.offerLast(root);

        for (int r = 1; r < h; r++) {
            printBranches(branchLen, nodeSpaceLen, startLen, nodesInThisLevel, nodesQueue);
            branchLen = branchLen / 2 - 1;
            nodeSpaceLen = nodeSpaceLen / 2 + 1;
            startLen = branchLen + (3 - level) + indentSpace;
            printNodes(branchLen, nodeSpaceLen, startLen, nodesInThisLevel, nodesQueue);

            for (int i = 0; i < nodesInThisLevel; i++) {
                TreeNode currentNode = nodesQueue.getFirst();
                nodesQueue.removeFirst();

                if (currentNode != null) {
                    nodesQueue.offerLast(currentNode.left);
                    nodesQueue.offerLast(currentNode.right);
                } else {
                    nodesQueue.offerLast(null);
                    nodesQueue.offerLast(null);
                }
            }
            nodesInThisLevel *= 2;
        }

        printBranches(branchLen, nodeSpaceLen, startLen, nodesInThisLevel, nodesQueue);
        printLeaves(indentSpace, level, nodesInThisLevel, nodesQueue);
    }

    private void printBranches(int branchLen, int nodeSpaceLen, int startLen, int nodesInThisLevel,
            Deque<TreeNode> nodesQueue) {
        Iterator<TreeNode> iter = nodesQueue.iterator();

        for (int i = 0; i < nodesInThisLevel / 2; i++) {
            int width = 0;
            if (i == 0) {
                width = startLen - 1;
            } else {
                width = nodeSpaceLen - 2;
            }

            TreeNode current = null;
            if (iter.hasNext()) {
                current = iter.next();

            }

            if (current != null) {
                printWithWidth(width, ' ', "/");
            } else {
                printWithWidth(width, ' ', "");
            }

            current = null;
            if (iter.hasNext()) {
                current = iter.next();
            }

            if (current != null) {
                printWithWidth(2 * branchLen + 3, ' ', "\\");
            } else {
                printWithWidth(2 * branchLen + 3, ' ', " ");
            }
        }
        System.out.println();
    }

    private void printLeaves(int indentSpace, int level, int nodesInThisLevel, Deque<TreeNode> nodesQueue) {
        Iterator<TreeNode> iter = nodesQueue.iterator();
        for (int i = 0; i < nodesInThisLevel; i++) {
            int width = i == 0 ? indentSpace : 2 * level + 2;

            TreeNode current = null;
            if (iter.hasNext()) {
                current = iter.next();
            }

            if (current != null) {
                printWithWidth(width, ' ', String.valueOf(current.val));
            } else {
                printWithWidth(width, ' ', " ");
            }
        }
        System.out.println();
    }

    private void printNodes(int branchLen, int nodeSpaceLen, int startLen, int nodesInThisLevel,
            Deque<TreeNode> nodesQueue) {
        Iterator<TreeNode> iter = nodesQueue.iterator();

        for (int i = 0; i < nodesInThisLevel; i++) {
            int width = (i == 0) ? startLen : nodeSpaceLen;

            printWithWidth(width, ' ', " ");

            TreeNode current = null;
            if (iter.hasNext()) {
                current = iter.next();
                if (current.left != null) {
                    printWithWidth(branchLen, '_', String.valueOf(current.val));
                } else {
                    printWithWidth(branchLen, ' ', String.valueOf(current.val));
                }
            } else {
                printWithWidth(branchLen + 1, ' ', " ");
            }

            if (current != null && current.right != null) {
                printWithWidth(branchLen, '_', " ");
            } else {
                printWithWidth(branchLen, ' ', " ");
            }
        }
        System.out.println();
    }

    private void printWithWidth(int width, char fillChar, String s) {
        for (int i = s.length(); i <= width; i++) {
            System.out.print(fillChar);
        }
        System.out.print(s);
    }
}

public class TreeNode {

    public TreeNode left;

    public TreeNode right;

    public int val;

    public TreeNode(int x) {
        val = x;

    }

    public int getHeight() {
        return getHeightHelper(this);
    }

    private int getHeightHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(getHeightHelper(node.left), getHeightHelper(node.right)) + 1;
    }

    public void print() {
        new PrettyPrinter(this, 1, 0).prettyPrint();
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

}
