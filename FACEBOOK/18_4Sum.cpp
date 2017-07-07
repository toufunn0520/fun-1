/**/

class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        //sorted 
        vector<vector<int>> res;
        int n = nums.size();
        if(n<4) return res;
        sort(nums.begin(), nums.end());
        
        for (int i = 0; i<nums.size()-3; i++) {
             //each position should not have the similar ones and also pruning at earlier phase
            if (i > 0 && nums[i] == nums[i-1]) continue;
            if (nums[i] + nums[i+1] + nums[i+2]+ nums[i+3] > target) break;
            if (nums[i] + nums[n-3] + nums[n-2] + nums[n-1] < target) continue;
            
            for (int j = i+1; j<nums.size()-2; j++) {
                
                if (j > i+1 && nums[j] == nums[j-1]) continue;
                if (nums[i] + nums[j] + nums[j+1]+ nums[j+2] > target) break;
                if (nums[i] + nums[j] + nums[n-2] + nums[n-1] < target) continue;
                
                  int start = j+1;
                  int end = n-1;
                
                  while (start < end) {
                      int cur = nums[i] + nums[j] + nums[start] + nums[end];
                      if (cur > target) {
                          end--;
                          while(start < end && nums[end] == nums[end+1]) end--;
                      } else if (cur < target){
                          start++;
                          while(start < end && nums[start] == nums[start-1]) start++;
                      } else {
                          res.push_back(vector<int>{nums[i], nums[j], nums[start], nums[end]});  // syntax
                          do{start++;}while(nums[start]==nums[start-1]&&start<end);
                          do{end--;}while(nums[end]==nums[end+1]&&end<start);
                      }
                  }
            }
        }
        
        return res;
    }
};
