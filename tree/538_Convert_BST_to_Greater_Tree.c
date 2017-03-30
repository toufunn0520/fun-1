void helper(struct TreeNode* root, int* sum) {
    if (!root) return 0;
    helper(root->right, sum);
    int tmp = *sum;
    *sum = (*sum) + (root->val);
    root->val += tmp;
    helper(root->left, sum);
    return;
}

struct TreeNode* convertBST(struct TreeNode* root) {
    int sum = 0;
    helper(root, &sum);
    return root;
}
