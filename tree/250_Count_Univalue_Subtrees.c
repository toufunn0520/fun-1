 // post order 
bool helper(struct TreeNode* root, int *count){
    if(!root) return true;
    bool left = helper(root->left, count);
    bool right = helper(root->right, count);
    /**/
    if(left && right){
          if(root->left && root->val!= root->left->val){
              return false;
          } 
          if(root->right && root->val !=root->right->val) {
              return false;
          }
          (*count)++;  // even the leaf node / or those only had left/right kids return  true
          return true;
    }
    return false;
}
int countUnivalSubtrees(struct TreeNode* root) {
    if(!root) return 0;
    int count = 0;
    helper(root, &count);
    return count;
}
