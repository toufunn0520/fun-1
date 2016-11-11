/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
 /*A bit abstract*/

int sumhelper(struct TreeNode* root, int cur, int sum){
    if(!root) return 0;
    cur = cur + root->val;
    //if(cur == sum) printf("val =%d\n", root->val);
    return ((cur == sum) + sumhelper(root->left, cur, sum) + sumhelper(root->right, cur, sum));
}

//pathSum is start point to any of its successor if cur could reach value "sum"
int pathSum(struct TreeNode* root, int sum) {
    if(!root) return 0;
    return (sumhelper(root, 0, sum) + pathSum(root->left, sum)+pathSum(root->right, sum));
}
