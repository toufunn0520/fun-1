/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
 /*Key is we cannot use divide and need to resolve it inconstant space*/
int* productExceptSelf(int* nums, int numsSize, int* returnSize) {
    *returnSize =0;
    if(!nums || numsSize ==0) return NULL;
    
    int * res = (int*) malloc(sizeof(int)* numsSize);
    *returnSize = numsSize;
    res[0] = nums[0];
    for(int i =1; i<numsSize; i++){
        res [i] = nums[i]* res[i-1];
    }
    int right = 1;
    
    for(int i = numsSize-1; i>=0; i--){
        if(i==0) {
            res[0] = right;
            break;
        }
        res[i] = res[i-1]* right;
        right = right * nums[i];
    }
    return res;
}
