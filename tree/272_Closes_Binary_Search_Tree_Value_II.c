/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
/*In order and postorder traverse*/

/*Get total nums of nodes*/
void getNumNodes(struct TreeNode* root, int *total){
    if(root){
    (*total)++;
    getNumNodes(root->left, total);
    getNumNodes(root->right, total);
        
    }
}
void getOrderNodes(struct TreeNode* root, int *res, int* index, double  target, bool isInorder) {
    if(!root) return;
    if(isInorder){
        getOrderNodes(root->left,res, index, target, isInorder);
        if(root->val <=target){
        res[(*index)] = root->val;
        (*index)++;
        }
        getOrderNodes(root->right,res, index, target, isInorder);
    }else{
        getOrderNodes(root->right,res, index, target, isInorder);
        if (root->val >target){
        res[(*index)] = root->val;
        (*index)++;
        }
        getOrderNodes(root->left,res, index, target, isInorder);
    }
    return;
}

double MIN(double p, double q) {return ((p<q)?p:q);}

void final(int *res, int k, double target, int *inorder, int inoderindex,int* postorder, int postorderindex){
    int i = 0;
    int j = 0;
    int cur = 0;
    while(inoderindex && postorderindex && (cur < k)){
        if((target-(double)inorder[inoderindex-1]) <= (postorder[postorderindex-1]-target)){
           res[cur] = inorder[inoderindex-1];
           inoderindex --;
        }else{
           res[cur] = postorder[postorderindex-1];
           postorderindex --;       
        }
        cur++;
    }
    
    while(cur<k){
        if(inoderindex){
            res[cur] = inorder[inoderindex-1];
            inoderindex--;
        }
        if(postorderindex){
            res[cur] = postorder[postorderindex-1];
            postorderindex--;
        }
        cur++;
    }
    return;
}

int* closestKValues(struct TreeNode* root, double target, int k, int* returnSize) {
    *returnSize = 0;
    if(!root) return NULL;
    int total = 0;
    getNumNodes(root, &total);
 
    int *inorder = malloc(sizeof(int)* total);
    int *postorder = malloc(sizeof(int)* total);
    int inorderindex = 0;
    getOrderNodes(root, inorder, &inorderindex, target, true);
    int postorderindex = 0;
    getOrderNodes(root, postorder, &postorderindex, target, false);

    int *res = malloc(sizeof(int)*k);
    *returnSize = k;
    final(res, k, target, inorder, inorderindex,postorder, postorderindex);
    return res;
}
