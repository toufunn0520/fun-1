Class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        //num is smaller than 3
        vector<vector<int>> res;
        res.clear();
        if(nums.size()<=2) return res;
        //sort-> bst for 2nd+3rd, and easy to ruleout dumplicate
        sort(nums.begin(), nums.end());
        for(int  i = 0; i<nums.size()-2;){
            int first = nums[i];
            int second = i+1;
            int last = nums.size()-1;
    
            while (second < last) {
                if (nums[second] + nums[last] == -first) {
                    res.push_back({first,nums[second],nums[last]});
                    last--;
                    second++;
                    while (nums[second] == nums[second-1] && second<last) second++; // key
                    while (nums[last] == nums[last+1] && last>second) last--;
                } else if (nums[second] + nums[last] > -first) {
                    last--;
                    while (nums[last] == nums[last+1] && last>second) last--; //key
                } else {
                    second++;
                    while (nums[second] == nums[second-1] && second<last) second++; //key
                }
            }
            //remove dumpicate
            i++;
            while(nums[i]==first && i<nums.size()-2) i++;
        }
        return res;
    }
};



class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>> res;
        res.clear();
        int size = nums.size();
        //sanity check
        if (size <= 2) return res;
        //sort
        sort(nums.begin(), nums.end());
        
        for (int i = 0; i < size-2; i++){
            int cur = nums[i];
            int second = i+1;
            int last = size -1;
            while (second<last) {
                bool forward = false;
                bool backward = false;
                if (nums[second] + nums[last] == -cur) {
                    res.push_back({cur,nums[second],nums[last]});
                    second++;
                    last --;
                    forward = true;
                    backward = true;
                }else if (nums[second] + nums[last] > -cur) {
                    last --;
                    backward = true;
                }else if(nums[second] + nums[last] < -cur){
                    second++;
                    forward = true;
                }
                while(forward && nums[second-1] == nums[second] && second < last) second++;
                while(backward && nums[last+1] == nums[last] && last > second) last--;
            }
            
            //remove dumplicated
            while(nums[i+1] == nums[i] && i < size-2) i++;
        }
        return res;
    }
};
