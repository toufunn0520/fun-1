class Solution {
public:
    void dfs(vector<vector<char>> pair, vector<string> &res, string digits, string tmp, int index) {
        if (index == digits.size()) {
            res.push_back(tmp);
            return;
        }
        int size = pair[digits[index]-'0'].size();
        for (int i = 0; i<size; i++ ) {
            dfs(pair,res,digits,tmp+pair[digits[index]-'0'][i],index+1);
        }
        return;
    }
    vector<string> letterCombinations(string digits) {
        vector<string> res;
        if(digits.size()==0) return res;
        vector<vector<char>> pair(2,vector<char>()); // 0-1 empty  !!!key
        pair.push_back(vector<char>{'a','b','c'}); // index 2   
        pair.push_back(vector<char>{'d','e','f'}); // 3
        pair.push_back(vector<char>{'g','h','i'});
        pair.push_back(vector<char>{'j','k','l'}); // 5
        pair.push_back(vector<char>{'m','n','o'});
        pair.push_back(vector<char>{'p','q','r','s'}); // 7
        pair.push_back(vector<char>{'t','u','v'});
        pair.push_back(vector<char>{'w','x','y','z'}); // 9

        dfs(pair, res,digits,"",0);
        return res;
    }
};
