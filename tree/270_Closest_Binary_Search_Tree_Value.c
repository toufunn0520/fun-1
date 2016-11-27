double MIN(double p, double q){
    return p<q? p:q;
}

int helper(struct TreeNode* root, double target, double *diff, int *cur){
    double tmp = *diff;
    int result = *cur;
    
    *diff = MIN(fabs((double)(root->val)-(double)target), *diff);

    if(tmp != *diff) {
        result = root->val;
        *cur = root->val;
    }
    //printf("MIN diff %f cur =%d\n", *diff, *cur);

    if(root->val < target && root->right) result = helper(root->right, target, diff, cur);
    if(root->val > target && root->left) result = helper(root->left, target, diff,cur);
    return result;
} 

int closestValue(struct TreeNode* root, double target) {
   double diff = 3000000001;
   int cur = root->val;
   return helper(root,target, &diff, &cur);
  
}
