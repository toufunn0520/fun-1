int MIN (int p, int q){
    return p<q?p:q;
}
int MAX(int p, int q){
    return p>q?p:q;
}
bool internal(struct TreeNode* root, int* res, int* min, int* max){
    if(!root) return true;
    int leftsize= 0;
    int rightsize = 0;
    int leftmin, rightmin = INT_MAX;
    int leftmax, rightmax = INT_MIN;
    
    bool isleft = internal(root->left, &leftsize, &leftmin, &leftmax);
    bool isright = internal(root->right, &rightsize, &rightmin, &rightmax);
    if(isleft && isright){
        if((!(root->left) || (root->val>leftmax)) && ((!root->right) || root->val < rightmin)) {
         *min = (root->left)? leftmin: root->val;
         *max = (root->right)? rightmax: root->val;
         *res = leftsize + rightsize +1;
         // printf("Update res =%d, min=%d, max=%d\n", *res, *min, *max);
          return true;
        }
    }
    *res = MAX(leftsize, rightsize);
    return false;
}
int largestBSTSubtree(struct TreeNode* root) {
    if(!root) return 0;
    int res = 0;
    int min= INT_MAX;
    int max = INT_MIN;
    internal(root, &res, &min, &max);
    return res;
}
