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
