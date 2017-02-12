/*rob_root = max(rob_L + rob_R , no_rob_L + no_nob_R + root.val)
no_rob_root = rob_L + rob_R*/

int MAX(int p, int q){
    return p>q?p:q;
}
int* rob_internal(struct TreeNode* root) {
    int *res = malloc(sizeof(int)*2);
    memset(res, 0, sizeof(int)*2);
    if(!root) return res;
    int *left = rob_internal(root->left);
    int *right = rob_internal(root->right);
    
    res[1] = left[0] + right[0] + root->val;  //root is chosen
    res[0] = MAX(left[0], left[1]) + MAX(right[0], right[1]);  // key
    //printf("check res[0]=%d res[1]=%d root=%d\n", res[0], res[1], root->val);
    return res;
}
int rob(struct TreeNode* root) {
    int *res;
    res = rob_internal(root);
    return MAX(res[0], res[1]);
}
