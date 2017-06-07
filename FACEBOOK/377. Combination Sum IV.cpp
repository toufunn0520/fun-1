//dp state function initiate: dp[nums[i]]= 1;  dp[X] += dp[X-nums[i]]; sort array to have prunning 
class Solution {
public:
    int combinationSum4(vector<int>& nums, int target) {
        vector<int> dp(target+1,0);
        dp[0] = 1; //important initail dp[1] = 1, dp[2]=1, dp[4]=1
        //sorted
        sort (nums.begin(), nums.end());
        for (int i = 0; i<= target; i++) {
            for (int j = 0; j<nums.size(); j++) {
                if (i>=nums[j]) {   // don't forget =, prunning
                    dp[i] += dp[i-nums[j]];
                }else {
                    break;
                }
            }
        }
        return dp[target];
    }
};
