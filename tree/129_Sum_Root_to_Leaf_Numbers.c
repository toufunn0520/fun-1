/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
 void internal(struct TreeNode* root, int cur_sum, int* sum) {
    if (!root) {
        return;
    }
    cur_sum = cur_sum*10 + root->val;
    if(!(root->left)&& !(root->right)){  // key
        *sum = *sum + cur_sum;
        return;
    }
    internal(root->left,cur_sum, sum);
    internal(root->right,cur_sum,sum);
    return;
}
int sumNumbers(struct TreeNode* root) {
    if(!root) return 0;
    int sum = 0;
    internal(root, 0, &sum);
    return sum;
}
