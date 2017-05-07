class Solution {
public:
    bool wordBreak(string s, vector<string>& wordDict) {
        unordered_set<string>dict;
        dict.insert(wordDict.begin(), wordDict.end());
        int len = s.size();
        vector<bool> dp(len, false);
        dp[0] = true;
        /* dp -> substring[0,i-1] do have segments in the dictionary
         * i is the len. j ranges from [0,i-1 ] dp[i] = dp[j]*dp[i-j] */
        for (int i = 1; i<=len; i++){
            for(int j = 0; j<i; j++){
                if(dp[j]){
                    string p = s.substr(j,i-j);
                    if(dict.find(p)!=dict.end()){
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        
        return dp[len];
    }
};
