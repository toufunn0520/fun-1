class Solution {
public:
    int maxSubArrayLen(vector<int>& nums, int k) {
        unordered_map<int, int> map;
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums[i];
            if (sum == k) {
                res = i+1;
            } else if (map.find(sum-k) != map.end()){   // smart keep (sum, index)
                res = max(res,i-map[sum-k]);    //range is (old,i]
            }
            if (map.find(sum) == map.end()) map[sum] = i;  // not all condition keep i as small as possible
        }
        return res;
    }
};
