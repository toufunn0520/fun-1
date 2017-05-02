/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
struct TreeNode* helper (struct TreeNode* root, struct TreeNode* p, struct TreeNode* q){
    if (!root) return NULL;
    if (root == p || root == q) return root;
    struct TreeNode* left = helper(root->left, p, q); 
    struct TreeNode* right = helper(root->right, p, q);
    if (left && right) return root;
    if (left) return left;
    if (right) return right;
    return NULL;
}
struct TreeNode* lowestCommonAncestor(struct TreeNode* root, struct TreeNode* p, struct TreeNode* q) {
    return helper(root, p, q);
}
