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
    Map<Integer, Integer> map; 
    int maxCount;
    List<Integer> res;
    public int[] findFrequentTreeSum(TreeNode root) {
         map = new HashMap<Integer, Integer>();
         res = new ArrayList<Integer>();
         postOrder(root);
         for(int key :map.keySet()){
             if(map.get(key) == maxCount){
                 res.add(key);
             }
         }
         int[] result = new int[res.size()];
         for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
         }
        return result;
    }
    private int postOrder(TreeNode root){
        if( root == null) return 0;
        int left = postOrder(root.left);
        int right = postOrder(root.right);
        int cur = root.val+left+right;
        // updaet the HASHMAP
        int count = map.getOrDefault(cur,0)+1;
        map.put(cur, count);
        maxCount = Math.max(maxCount, count);
        return cur;
    }
}
