/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer>cur_res = new LinkedList<Integer>();
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if(root!=null) helper(root, sum, res, cur_res);
        return res;
    }
    private void helper(TreeNode root, int sum,List<List<Integer>>res,List<Integer>cur_res){
        if(root==null) return;
        //leaf nodes:
        cur_res.add(new Integer(root.val));
        if(root.left == null && root.right == null && sum == root.val){
            res.add (new LinkedList(cur_res));
            cur_res.remove(cur_res.size()-1);  //key
            return;
        }else{
            helper(root.left, sum - root.val, res, cur_res);
            helper(root.right, sum - root.val, res, cur_res);
        }
        cur_res.remove(cur_res.size()-1); //key
    }
}
