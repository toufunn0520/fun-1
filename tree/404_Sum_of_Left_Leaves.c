/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
void helper(struct TreeNode* root, int* sum){
    // leaf nodes
    if(root && !root->left && !(root->right)){
        (*sum) += root->val;
//        printf("Update ++ %d\n", root->val);
    }
    if(root->left) helper(root->left, sum);
    if(root->right) helper(root->right, sum);
// the parent of right leaf
    if(root && root->right && (!(root->right->right) &&!(root->right->left))){
        (*sum) -= root->right->val;
 //       printf("Update -- %d\n", root->right->val);
        return;
    }
} 

int sumOfLeftLeaves(struct TreeNode* root) {
    int result =0;
    if (!root || (root && !root->left && !root->right)) return result;
    helper(root, &result);
    return result;
}
