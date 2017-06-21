/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    vector<vector<int>> verticalOrder(TreeNode* root) {
        vector<vector<int>> res;
        if (!root) return res;
        
        map<int, vector<int>> level;    //saved the level, [relatively]
        queue<pair<int, TreeNode*>> q;  //saved the  next level traverse
        q.push(make_pair(0,root));
        
        while (!q.empty()) {
            TreeNode* cur = q.front().second;
            int l = q.front().first;
            q.pop();
            level[l].push_back(cur->val);
            if (cur->left) q.push(make_pair(l-1,cur->left));
            if (cur->right) q.push(make_pair(l+1,cur->right));
        }
        
        for (auto &v: level) {
            res.push_back(v.second);  // key
        }
        
        return res;
    }
};
