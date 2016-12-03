/**
 * Return an array of arrays of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 * 1. k-sum问题可以转化为(k-1)-sum问题：对于数组中每个数A[i]，在A[i+1:n-1]中寻找target-A[i]的(k-1)-sum问题。
 * 2. 直到k=2时，用2sum的双指针扫描来完成。
 */
int compare(const void* p, const void* q){
    int l = *(const int*)p;
    int r = *(const int*)q;
    return (l-r);
}
void helper(int* nums, int numsSize, int target, int* returnSize, int** res, int* tmp, int* cur, int start, int end){
    
    if((*cur) == 2){
    //    printf("start from %d end at %d", start, end);
        while(start<end) {
            int sum = nums[start]+nums[end];
            if(sum == target) {
              tmp[2] = nums[start];
              tmp[3] = nums[end];
              res[(*returnSize)] = (int*)malloc(sizeof(int)*4);
              memcpy(res[(*returnSize)], tmp, 4*(sizeof(int)));
              (*returnSize)++;
     /*         printf("Bingo! pick %d %d \n", start, end);
              for(int j =0; j<4; j++){
                  printf("\t%d\n", tmp[j]);
              }
     */
              start++;
              end--;
            while(nums[start]==nums[start-1]) start++;
            while(nums[end]==nums[end+1]) end--;
            }else if(sum<target){ 
                start++;
            }else{
                end--;
            }
        }
        return;
    }
    
    for(int i = start; i<=end; i++){
        if(i>start && nums[i]==nums[i-1]) continue;
        tmp[(*cur)] = nums[i];
        (*cur)++;
        helper(nums, numsSize, target-nums[i], returnSize, res, tmp, cur, i+1, end);
        (*cur)--;
    }
    
}

int** fourSum(int* nums, int numsSize, int target, int* returnSize) {
    *returnSize = 0;
    if(!nums || !numsSize) return NULL;
    int MAX = numsSize*4;
    int** res = (int**)malloc(sizeof(int*)* MAX);
    int* tmp = (int*)malloc(sizeof(int)*4);
    int cur =0;
    //sort
    qsort((void*)nums, numsSize, sizeof(int), compare);
  //  for(int i =0; i<numsSize; i++) printf("The %dth is %d\n", i, nums[i]);
    helper(nums, numsSize, target, returnSize, res, tmp, &cur, 0, numsSize-1);
    return res;
}
