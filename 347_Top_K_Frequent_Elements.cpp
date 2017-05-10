
class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> map;
        vector<int> res;
        res.clear();
        map.clear();
        
        for(int i = 0; i<nums.size(); i++) {
            map[nums[i]]++;
        }
        
        priority_queue<pair<int, int>> que;
        for (auto it : map) {
            que.push(make_pair(it.second, it.first));
        }
        
        while(k>0){
            res.push_back(que.top().second);
            k--;
            que.pop();
        }
        
        return res;
    }
};

/*class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> map;
        vector<int> res;
        res.clear();
        map.clear();
        for(int i = 0; i<nums.size(); i++) {
            map[nums[i]]++;
        }
        vector<vector<int>> bucket(nums.size()+1);
        
        for (auto it : map) bucket[it.second].push_back(it.first);
        for (int i = nums.size(); i >=0 && k>0; i--) {
            for (auto iter: bucket[i]){
                res.push_back(iter);
                k--;
                if(!k) break;
            }
        }
        return res;
    }
};*/
