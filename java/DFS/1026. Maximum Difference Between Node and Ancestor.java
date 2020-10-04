// for each subtree find both min and max and keep updating global variable maxDiff
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int maxDiff = 0;
    public int maxAncestorDiff(TreeNode root) {
        maxDiff = 0;
        maxAncestorDiffInternal(root, 0);
        return maxDiff;
    }
    private int[] maxAncestorDiffInternal(final TreeNode root, int prev) {
        if (root == null) {
            return new int[]{prev,prev};
        }
        
        int left[] = maxAncestorDiffInternal(root.left, root.val);
        int right[] = maxAncestorDiffInternal(root.right, root.val);
        
        int min= Math.min(left[0],right[0]);
        int max= Math.max(left[1],right[1]);

        // find the |difference|  
        int diff= Math.max(Math.abs(min-root.val),Math.abs(max-root.val));
        if(diff > maxDiff)
            maxDiff=diff;
			
        // update the min and max value for current tree
        return new int[]{Math.min(min,root.val),Math.max(max,root.val)};
    }
}
