package category.tree.trinarytree;

class Node {

    Node left;

    Node mid;

    Node right;

    int val;

    public Node(int key) {
        this.val = key;
        this.left = this.mid = this.right = null;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}

public class TrinaryTree {

    public static int countNodes(Node node) {
        int count = 1;
        if (node.left != null)
            count += countNodes(node.left);
        if (node.mid != null)
            count += countNodes(node.mid);
        if (node.right != null)
            count += countNodes(node.right);
        return count;
    }

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {
        int[] vals = { 5, 4, 9, 5, 7, 2, 2 };
        TrinaryTree trinaryTree = new TrinaryTree();
        trinaryTree.insert(vals);
        System.out.println("Tree after inserting nodes : ");
        print(trinaryTree.root);
        System.out.println("Nodes : " + countNodes(trinaryTree.root));
        trinaryTree.delete(trinaryTree.root, 7);
        System.out.println("Tree after deleting a node : ");
        print(trinaryTree.root);
        System.out.println("Nodes : " + countNodes(trinaryTree.root));
    }

    /**
     * Prints the Trinary Tree..
     *
     * @param root
     *            the root
     */
    public static void print(Node root) {
        if (root != null) {
            System.out.println("Node : " + root.val);
            print(root.left);
            print(root.mid);
            print(root.right);
        }
    }

    Node root;

    public TrinaryTree() {
        this.root = null;
    }

    private void delete(int val) {
        if (root == null) {
            return;
        } else {
            root = delete(root, val);
        }
    }

    /**
     * This method deletes a node in the Trinary Tree.
     *
     * @param node
     *            the node
     * @param val
     *            the val
     * @return the trinary node
     */
    public Node delete(Node node, int val) {
        if (node == null) {
            return null;
        }

        if (val < node.val) {
            node.left = delete(node.left, val);
        } else if (val > node.val) {
            node.right = delete(node.right, val);
        } else {
            if (node.mid != null) {
                node.mid = delete(node.mid, val);
            } else if (node.right != null) {
                node.val = findMin(node.right).val;
                node.right = delete(node.right, node.val);
            } else {
                node = node.left;
            }
        }
        return node;
    }

    /**
     * This method finds the minimum node.
     *
     * @param node
     *            the node
     * @return the trinary node
     */
    private Node findMin(Node node) {
        if (node != null) {
            while (node.left != null)
                return findMin(node.left);
        }
        return node;
    }

    public void insert(int val) {
        if (root == null) {
            root = new Node(val);
        } else {
            insert(root, val);
        }
    }

    /**
     * This method inserts a node in the Trinary Tree..
     *
     * @param vals
     *            the vals
     */
    public void insert(int[] vals) {
        if (vals != null) {
            if (vals.length != 0) {
                Node node = new Node(vals[0]);
                for (int i = 1; i < vals.length; i++)
                    insert(node, vals[i]);
                this.root = node;
            }
        }
    }

    /**
     * This method Inserts a node in the Trinary Tree.
     *
     * @param node
     *            the node
     * @param val
     *            the val
     */
    public void insert(Node node, int val) {
        if (val < node.val) {
            if (node.left != null) {
                insert(node.left, val);
            } else {
                node.left = new Node(val);
            }
        } else if (val > node.val) {
            if (node.right != null) {
                insert(node.right, val);
            } else {
                node.right = new Node(val);
            }
        } else {
            if (node.mid != null) {
                insert(node.mid, val);
            } else {
                node.mid = new Node(val);
            }
        }
    }
}
