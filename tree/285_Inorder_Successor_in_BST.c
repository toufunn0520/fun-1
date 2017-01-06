struct TreeNode* helper(struct TreeNode* root, struct TreeNode* p){
    if(!root) return NULL;
    
    if(root->val <= p->val){    // key <= edge condition if there is only one node and try find its 
        return helper(root->right, p);
    }else{
        struct TreeNode* left = helper(root->left, p);
        return left?left : root;
    }
     
}
struct TreeNode* inorderSuccessor(struct TreeNode* root, struct TreeNode* p) {
    return helper(root, p);
}



/*
public TreeNode predecessor(TreeNode root, TreeNode p) {
  if (root == null)
    return null;

  if (root.val >= p.val) {
    return predecessor(root.left, p);
  } else {
    TreeNode right = predecessor(root.right, p);
    return (right != null) ? right : root;
  }
}

*/
