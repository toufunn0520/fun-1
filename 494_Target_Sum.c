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

/*DP solution*/
/*dp[n][s] = dp[n-1][s-a[n-1]]+ dp[n-1][s+a[n-1]]
 * Total sum of the array is B
 * since the second demention is half negative from -B to B so we need to map it to 0->2B
 * Init dp[0][a[0]] = dp[0][-a[0]] = 1
 * If a[0] = need to init dp[a][a[0]] = 2 
 */
int findTargetSumWays(int* nums, int numsSize, int S) {
    
    if(!nums || !numsSize) return 0;
    int s = 0;
    for (int i = 0; i<numsSize; i++) {
        s += nums[i];
    }
    if(S>abs(s)) return 0;

    /*Init dp array*/
    int secondSize = 1 + (s<<1) ;
    int **dp = malloc(sizeof(int*) *numsSize);
    for (int i =0; i<numsSize; i++) {
        dp[i] = malloc(sizeof(int) * secondSize);
        for (int j = 0; j<secondSize; j++) {
            dp[i][j] = 0;
        }
    }
    dp[0][s+nums[0]] =1;
    dp[0][s-nums[0]] +=1;
            

    for (int i = 1; i< numsSize; i++) {
        for (int j = 0; j<secondSize; j++) {
            /*Check Range pruning unavailable result*/
            if (j>=nums[i]){
                dp[i][j] += dp[i-1][j-nums[i]];
            }
            if(j+nums[i]<secondSize){
                dp[i][j] += dp[i-1][j+nums[i]];
            }
        }
    }
    
    return dp[numsSize-1][S+s];
}
