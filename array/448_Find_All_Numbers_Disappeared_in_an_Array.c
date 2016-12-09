/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
 //smart use signed to define if the number is saw
 int abs(int p){
     return (p>0)?p:-p;
 }
int* findDisappearedNumbers(int* nums, int numsSize, int* returnSize) {
    (*returnSize) =0;
    if(!nums || numsSize==0) return NULL;
    int* res = (int*)malloc(sizeof(int)*numsSize);
    for(int i =0; i<numsSize; i++){
        if(nums[abs(nums[i])-1]>0) {
            nums[abs(nums[i])-1] = -nums[abs(nums[i])-1];
         //   printf("set %d number %d as visited %d\n",i, nums[i],nums[abs(nums[i])-1]);
        }
    }
    
    for(int i =1; i<=numsSize; i++){
        if(nums[i-1]> 0) {
            res[(*returnSize)] = i;
            (*returnSize)++;
         //   printf("Bingo %d\n",i);
        }
    }
    return res;
}
