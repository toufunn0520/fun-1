/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
int MAX(int p, int q){
    return p>q?p:q;
}
int GetTreedepth(struct TreeNode* root){
    if(!root) return 0;
    int left = GetTreedepth(root->left);
    int right = GetTreedepth(root->right);
    int cur_depth = MAX(left, right)+1;
    return cur_depth;
}
void helper(struct TreeNode* root, int depth, int* result, int *index){
    if(!root) return;
    if( depth == *index){  // smart
        result[*index] = root->val;
        (*index)++;
    }
    helper(root->right, depth+1, result, index);
    helper(root->left, depth+1, result, index);
    return;
}

int* rightSideView(struct TreeNode* root, int* returnSize) {
    // get depth?
    *returnSize=0;
    if(!root) return NULL;
    int depth = GetTreedepth(root);
    int* result = malloc(sizeof(int)* (depth));
    /*BFS and start from right*/
    helper(root, 0,result,returnSize);
    return result;
}
