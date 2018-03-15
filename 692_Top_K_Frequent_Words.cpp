class Solution {
public:
    struct Comp {
         bool operator()(const pair<int, string>& lhs, const pair<int, string>& rhs) const {
            if (lhs.first != rhs.first)
                return lhs.first < rhs.first;
            return lhs.second > rhs.second;
        }
    };
    vector<string> topKFrequent(vector<string>& words, int k) {
        
        //reconstruct input into <word, count>
        unordered_map<string, int> input;
        vector<string> res;
        input.clear();
        res.clear();
        
        for (auto it : words) {
            input[it]++;
        }
        
        //remapped into an priority_queue <count,words>
        priority_queue<pair<int, string>, vector<pair<int,string>>, Comp> que;
        for (auto it : input) {
            que.push(make_pair(it.second, it.first));
        }
        //finally genearte result
        while (k>0) {
            res.push_back(que.top().second);
            que.pop();
            k--;
        }
        return res;

    }
};
