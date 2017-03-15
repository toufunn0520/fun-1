int* findDuplicates(int* nums, int numsSize, int* returnSize) {
    *returnSize =0;
    int *res = malloc(sizeof(int)*numsSize);
    for(int i =0; i< numsSize; i++){
        int index = abs(nums[i])-1;
        if(nums[index] <0) {
            res[*returnSize] = index+1;
            (*returnSize)++;
        }else {
            nums[index] = -nums[index];
        }
    }
    return res;
}
