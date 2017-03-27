 /**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
struct Node {
    struct Node *left;
    struct Node *right;
    int sum;
    int dup;
    int val;
};
struct Node* newNode(int val, int sum){
    struct Node* node = malloc(sizeof(struct Node));
    node->left = NULL;
    node->right = NULL;
    node->sum = sum;
    node->dup = 1;
    node->val = val;
    return node;
}
void* insert(struct Node** node, int* res, int curval, int index, int presum){
    if (*node == NULL){
        *node = newNode(curval, 0);
        res[index] = presum;
        return;
    }else{
        if ((*node)->val == curval) {
            ((*node)->dup)++;
            res[index] = presum+ (*node)->sum; // don't forget
            return;
        }else if((*node)->val < curval){
            insert(&((*node)->right), res, curval, index, presum+((*node)->sum)+((*node)->dup));
        }else {
            ((*node)->sum)++;
            insert(&((*node)->left), res, curval, index, presum);
        }
    }
        
    return;
}
int* countSmaller(int* nums, int numsSize, int* returnSize) {
    *returnSize = 0;
    int * res = NULL;
    if (!nums || !numsSize) return NULL;
    res = malloc(sizeof(int)*numsSize);
    struct Node *root = NULL;
    
    for (int i = numsSize-1; i>=0; i--) {
        insert(&root, res, nums[i], i, 0);
    }
    *returnSize = numsSize;
    return res;
}
