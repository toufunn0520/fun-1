class Solution {
public:
    bool helper(TreeNode* root, long long* curmax){
        if(root == NULL) return true;
        if(!helper(root->left, curmax)) return false;
        if( (*curmax) < root->val) {
            *curmax = root->val;
        }else {
            return false;
        }
        return helper(root->right, curmax);
    }
    bool isValidBST(TreeNode* root) {
        if(root == NULL) return true;
        long long curmax = LONG_MIN;  !!! testcase INT_MIN 
        return helper(root, &curmax);
    }
};
