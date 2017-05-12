class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int len = nums.size();
        if (!len) return len;
        int count = 0;
        int res = 0;
        
        for (int i = 0; i<len; i++) {
            if (nums[i] == 1) {
                count++;
                res = max(count,res);
            } else {
                count = 0;
            }
        }
        return res;
    }
};
