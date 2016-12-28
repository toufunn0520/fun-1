struct TreeNode* upsideDownBinaryTree(struct TreeNode* root) {
    if(!root) return root;
    struct TreeNode* cur = root;
    struct TreeNode* parent = NULL;
    struct TreeNode* right = NULL;
    while(cur){
        struct TreeNode* left = cur->left;
        cur->left = right;
        right = cur->right;
        cur->right = parent;
        parent = cur;
        cur = left;
    }
    return parent;
}
