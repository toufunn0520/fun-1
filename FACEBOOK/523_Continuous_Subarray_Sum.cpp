class Solution {
public:
    bool checkSubarraySum(vector<int>& nums, int k) {
        unordered_map<int, int> map;
        map[0] = -1;  // corner case for two leading zeros! 
        k = abs(k);
        int sum = 0;
        
        for (int i = 0; i < nums.size(); i++) {
            sum += nums[i];
            if (k) sum = sum % k;
            if (map.find(sum) != map.end()) {
                if (i-map[sum] > 1) return true;   //Smart!!! once we hit the same mod which indecate from(i,j] must be k*n
            } else {
                map[sum] = i;   //(i.j]   
            }
        }
        return false;
        
    }
};
