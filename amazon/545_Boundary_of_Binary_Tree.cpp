class Solution {
public:
    void helper(TreeNode* root, vector<int>& res, bool tl, bool tr ) {
        if(!root) return;
        if(tl) res.push_back(root->val);
        if(!tl && !tr && !(root->left) && !(root->right)) res.push_back(root->val);
        helper(root->left, res, tl, tr&&!(root->right));
        helper(root->right, res, tl&&(!root->left), tr);
        if(tr) res.push_back(root->val);
    }
    
    vector<int> boundaryOfBinaryTree(TreeNode* root) {
        vector<int> res ;
        if (root) {
            res.push_back(root->val);
            helper(root->left, res, true, false);
            helper(root->right, res, false, true);
        }
        return res;
    }
};
