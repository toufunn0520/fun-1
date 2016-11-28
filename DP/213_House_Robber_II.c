
/*Brillant! 我们这里变通一下，如果我们把第一家和最后一家分别去掉*/
int MAX (int p, int q){
    return p>q?p:q;
}
/*Same function as robber I*/
/* dp[i]  = MAX(num[i] + dp[i - 2], dp[i - 1])*/
int helper(int* nums, int start, int end){
    
    if(start == end-1) return nums[start];
    if(start +1 == end-1)  return MAX(nums[start], nums[start+1]);
    
    int *res = (int*)malloc(3*sizeof(int));
    res[0] = nums[start];
    res[1] = MAX(nums[start], nums[start+1]);
    int ans = 0;
    for(int i =start+2; i<end; i++){
        res[i%3] = MAX((nums[i]+res[(i-2)%3]),res[(i-1)%3]);
        ans= MAX(ans, res[i%3]);  //important
        //printf("curren ans =%d, res0=%d; res1=%d, res2=%d \n", ans,res[(i-2)%3],res[(i-1)%3] , res[i%3]);
    }
    return ans;
}

/*MAIN Function*/
int rob(int* nums, int numsSize) {
    if(!nums || numsSize ==0) return 0;
    if(numsSize == 1) return nums[0];
    if(numsSize == 2) {
        return MAX(nums[0], nums[1]);
    }
    
    return MAX(helper(nums, 0, numsSize-1), helper(nums++, 0, numsSize-1)); 
    // nums++ important otherwise the for loop of res won't be start from res[2]
}
