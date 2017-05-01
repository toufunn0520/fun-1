class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        vector<int> res;
        unordered_map<int, int> map;
        res.clear();
        if (!nums.size()) return res;
        vector<int>::iterator it = nums.begin();
        int index = 0;
        for(;it!=nums.end(); it++){
            if(map.find(target-(*it))!= map.end()){
                res.push_back(map[target-(*it)]);
                res.push_back(index);
                break;
            }else{
                map.insert({*it,index});
            }
            index++;
        }
        return res;
    }
};
