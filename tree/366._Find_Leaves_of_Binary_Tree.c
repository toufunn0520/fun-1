/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *columnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
 int MAX(int p, int q){
     return p>q?p:q;
 }
 int helperdepth(struct TreeNode* root){
     if(!root) return 0;
     int depth = 1+MAX(helperdepth(root->left),helperdepth(root->right));
     return depth;
 }
int helper(struct TreeNode* root,int** columnSizes, int** res){
    
    if(!root) return -1;
    int depth = 1+MAX(helper(root->right, columnSizes, res),helper(root->left, columnSizes, res));
  //  printf("depth =%d index =%d val =%d\n", depth, (*columnSizes)[depth], root->val);
    res[depth][(*columnSizes)[depth]] = root->val;
    (*columnSizes)[depth] ++;
    return depth;
}

int** findLeaves(struct TreeNode* root, int** columnSizes, int* returnSize) {
    *returnSize =0;
    if(!root) return NULL;
    int maxdepth = helperdepth(root);
   // printf("Tree depth is %d\n", maxdepth);
    
    *returnSize = maxdepth;
    int **res= (int**)malloc(sizeof(int*)*maxdepth);

    *columnSizes = (int*) malloc(sizeof(int)*maxdepth);
    for(int i =0; i<maxdepth; i++){
        res[i] = (int*)malloc(sizeof(int)*100);
        (*columnSizes)[i] =0;
    }
    helper(root, columnSizes, res);
    return res;
    
}
