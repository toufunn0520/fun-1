/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
 void swap(struct TreeNode* root, struct TreeNode* parent, bool isleft){
     struct TreeNode* tmp = NULL;
     struct TreeNode* tmp_parent = NULL;
     if(!root->left && !root->right) {
         goto out;
     }
     if(root->right){
         tmp = root->right;
         tmp_parent = tmp;
         while(tmp->left){
             tmp_parent = tmp;
             tmp = tmp->left;
         }
         tmp->left = root->left;
        if (root->right != tmp) {
             tmp_parent->left = tmp->right;
             tmp->right = root->right;
         }
         goto out;
     }
     if(root->left){
         tmp = root->left;
         tmp_parent = tmp;
         while(tmp->right){
             tmp_parent = tmp;
             tmp = tmp->right;
         }
         tmp->right = root->right;
         if (root->left != tmp) {
            tmp_parent->right = tmp->left;
            tmp->left = root->left;
         }
         goto out;
     }
out:
    if(isleft) {
        parent->left = tmp;
    }else{
        parent->right = tmp;
    }
    return;
 }
 
 void BSTfind (struct TreeNode* root, int key, struct TreeNode* parent, bool isleft){
    if (!root) return;
    
    if (root->val>key) BSTfind(root->left, key, root, true);
    if (root->val<key) BSTfind(root->right, key, root, false);
    if (root->val == key) swap(root, parent, isleft);
    return ;

}
struct TreeNode* deleteNode(struct TreeNode* root, int key) {
    if (!root) return root;
    struct TreeNode* dummy = malloc(sizeof(struct TreeNode));
    dummy->left = root;
    dummy->right = NULL;
    BSTfind(root, key, dummy, true);
    //printf("new root=%d\n", dummy->left ->val);
    return dummy->left;
}
/*JAVA */
public TreeNode deleteNode(TreeNode root, int key) {
    if(root == null){
        return null;
    }
    if(key < root.val){
        root.left = deleteNode(root.left, key);
    }else if(key > root.val){
        root.right = deleteNode(root.right, key);
    }else{
        if(root.left == null){
            return root.right;
        }else if(root.right == null){
            return root.left;
        }
        
        TreeNode minNode = findMin(root.right);
        root.val = minNode.val;
        root.right = deleteNode(root.right, root.val);
    }
    return root;
}

private TreeNode findMin(TreeNode node){
    while(node.left != null){
        node = node.left;
    }
    return node;
}
