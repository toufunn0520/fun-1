/*Using DFS + pruning impossible result*/

void helper(int* nums, int numsSize, int S, int index, int cursum, int* s, int* result){
    if(index == numsSize){
        if(cursum == S) (*result)++;
        return;
    }
    if(abs(S-cursum)<=s[index]){
        helper(nums,numsSize,S, index+1, cursum+nums[index], s, result);
        helper(nums,numsSize,S, index+1, cursum-nums[index], s, result);
    }
}

int findTargetSumWays(int* nums, int numsSize, int S) {
    if(!nums || !numsSize) return 0;
    int *s = malloc(sizeof(int)*numsSize);
    
    s[numsSize-1] = nums[numsSize-1];
    for(int i = numsSize-2; i>=0; i--){
        s[i] = s[i+1] + nums[i];
    }
    
    int result = 0;
    helper(nums, numsSize, S, 0, 0,s, &result);
    return result;
}
