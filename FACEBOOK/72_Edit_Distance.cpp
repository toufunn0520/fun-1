class Solution {
public:
    int minDistance(string word1, string word2) {
        int s = word1.size();
        int t = word2.size();
        vector<vector<int>>dp(s+1, vector<int>(t+1, 0));
        for (int i=1; i<s+1; i++) {
            dp[i][0] = i;
        }
        for (int j=1; j<t+1; j++) {
            dp[0][j] = j;
        }     
        for (int i=1; i<=s; i++) {
            for (int j=1; j<=t; j++) {
                if (word1[i-1] == word2[j-1]) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // insertation or deletition or replace
                    dp[i][j] = min(dp[i-1][j], min(dp[i][j-1], dp[i-1][j-1]))+1;
                }
            }
        }
        return dp[s][t];
    }
};

/*
Replace word1[i - 1] by word2[j - 1] (dp[i][j] = dp[i - 1][j - 1] + 1 (for replacement));
Delete word1[i - 1] and word1[0..i - 2] = word2[0..j - 1] (dp[i][j] = dp[i - 1][j] + 1 (for deletion));
Insert word2[j - 1] to word1[0..i - 1] and word1[0..i - 1] + word2[j - 1] = word2[0..j - 1] 
(dp[i][j] = dp[i][j - 1] + 1 (for insertion)).
*/
