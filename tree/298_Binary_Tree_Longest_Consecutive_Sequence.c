/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
void helper(struct TreeNode* root, int parent, int cur, int* res){
    if(!root) return;
    if(root->val == parent+1){
        cur ++;
        if(cur > *res) {
            *res = cur;
        }
    }else{
        cur = 1;
    }
    helper(root->left, root->val, cur,res);
    helper(root->right, root->val, cur,res);
    return;
}
int longestConsecutive(struct TreeNode* root) {
    if (!root) return 0;
    int res = 0;
    helper(root, root->val-1, 0,&res);
    return res;
}
