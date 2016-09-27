/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
 
 
 /*    int kthSmallestDFS(TreeNode* root, int &k) {
        if (!root) return -1;
        int val = kthSmallestDFS(root->left, k);
        if (!k) return val;
        if (!--k) return root->val;
        return kthSmallestDFS(root->right, k);
    }*/
int helper (struct TreeNode* root, int k, int* i){
    if(!root) return -1;
    int val = helper(root->left, k,i);
    if(*i== k-1) {
        (*i)++;
      //  printf("Found i=%d val=%d\n", *i, root->val);
        return root->val;
    }else if ((*i)==k) {
       // printf("Simply return i=%d val=%d\n", *i, val);
        return val;
    }else{
        (*i)++;
        printf("current i=%d", *i);
    }
    return helper(root->right, k, i);
}
 
/*In order traverse*/
int kthSmallest(struct TreeNode* root, int k) {
    int cur =0;
    return helper(root,k, &cur);
}
