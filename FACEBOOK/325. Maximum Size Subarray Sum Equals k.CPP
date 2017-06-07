class Solution {
public:
    int maxSubArrayLen(vector<int>& nums, int k) {
        if(nums.size()==0) return 0;
        int res = 0;
        int sum = 0;
        unordered_map<int, int> map;
        for (int i = 0; i<nums.size(); i++) {
            sum += nums[i];
            if (sum == k) {
                 res = max(res,i+1);
            } else if (map.find(sum-k) != map.end()) {
                res = max(res, i-map[sum-k]);   // careful no +1 exclude sum-k start index
            }
            if (map.find(sum) == map.end() ) map[sum] = i;  //! not sum-k 
            
        }
        
        return res;
    }
};

