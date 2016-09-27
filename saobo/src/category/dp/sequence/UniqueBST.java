package category.dp.sequence;

public class UniqueBST {

    public static void main(String[] args) {
        System.out.println(new UniqueBST().numTrees(3));

    }

    /**
     * [Leetcode 96] https://leetcode.com/problems/unique-binary-search-trees/
     * 
     * <pre>
     * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
     *
     * For example,
     * Given n = 3, there are a total of 5 unique BST's.
     *  1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     * </pre>
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] numTrees = new int[n + 1];

        numTrees[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                numTrees[i] += numTrees[j] * numTrees[i - j - 1];
            }
        }

        return numTrees[n];
    }

}
