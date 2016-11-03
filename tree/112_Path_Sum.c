/*
 */
//at leave level check if it reaches; pre-order 
//global stop
// not all path. But path from root to leaf
void helper(struct TreeNode* root, int sum, int cur, bool* stop){
     if(*stop) return;
     if (!root) return;
     // leavf
     if(root && !(root->left) && !(root->right)) {
         if(cur+ root->val ==sum) {
             *stop = true; 
             printf("Bingo\n");
             return;
         }
     }
     int updated = cur+root->val;
     helper(root->left, sum, updated, stop);
     helper(root->right, sum, updated, stop);
 }
 
bool hasPathSum(struct TreeNode* root, int sum) {
    bool stop = false;
    if(root) helper(root, sum, 0, &stop);
    return stop;
    
}
