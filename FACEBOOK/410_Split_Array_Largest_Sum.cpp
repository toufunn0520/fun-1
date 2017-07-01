/*BST search*/

class Solution {
public:
    bool valid(vector<int>& nums, int m, int target) {
        int count = 1;  // key  check if execeed m 
        int cur = 0;
        for (auto it: nums) {
            cur += it;
            if(cur > target){
                count ++;
                if(count > m) return false;
                cur = it;
            }
        }   
        return true;
    }
    int bst(vector<int>& nums, int m, int upper, int lower) {
       int l = lower;
       int u = upper;
       while (l<u) {
            int mid = l + (u-l)/2;
            if(valid(nums, m, mid)){
               u = mid;
            }else{
               l = mid+1;
            }
       }
       return l;
    }
    
    int splitArray(vector<int>& nums, int m) {
        int total = 0;
        int maxx = nums[0];
        for(auto it: nums){
            total += it;
            maxx = max(maxx, it);
        }
        return bst(nums, m, total, maxx);
        
    }
};
