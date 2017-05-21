class Solution {
public:
    int lengthOfLongestSubstringTwoDistinct(string s) {
        int dict[256] = {0};
        int res = 0;
        int start = 0;
        int count = 0;
        
        for (int i = 0; i<s.length();i++) {
            dict[s[i]]++;
            if (dict[s[i]] == 1) count++;  !! key
            while (count > 2) {
               if(--dict[s[start]] == 0){
                   count--;   !! key
               } 
               start++;    !!key
            }
            if (i-start+1>res) res = i-start+1;
            
        }
        return res;
    }
};
