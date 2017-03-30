int MAX(int p, int q){
    return p>q?p:q;
}
int maxdepth (struct TreeNode* root, int *res) {
    if(!root) return 0;
    int left = maxdepth(root->left, res);
    int right = maxdepth(root->right, res);
    *res = MAX(*res, left+right);   //key intersting not the longest path but distance
    return MAX(left,right)+1;

}
int diameterOfBinaryTree(struct TreeNode* root) {
    int res = 0;
    maxdepth(root, &res);
    return res;
}
