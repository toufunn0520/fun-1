class Solution {
    
public:
    int n;
    unordered_map<string, vector<string>> dict;
    vector<string> square;
    vector<vector<string>> res;
    vector<vector<string>> wordSquares(vector<string>& words) {
        /*build */
        n = words[0].size();   //key
        square.resize(n);
        for (string it: words) {
            for (int i = 0; i<n; i++) {
                string tmp = it.substr(0,i);  //smart <prefix, lsstof word>
                dict[tmp].push_back(it);
            }
        }
        build(0);
        return res;
        
    }
    void build(int level){
        /*if find all matches!*/
        if (level == n) {
            res.push_back(square);
            return;
        }
        string target;
        for (int i = 0; i<level; i++) {
            target += square[i][level];
        }
        for (string it: dict[target]) {
            square[level] = it;
            build(level+1);
        }
    }
    
};
