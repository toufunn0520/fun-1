class Solution {
public:
    unordered_map<char, int> map;
    int lengthOfLongestSubstringKDistinct(string s, int k) {
        map.clear();
        int res = 0;
        int len = s.length();
        int j = -1;
        
        for (int i = 0; i< len; i++) {
            map[s[i]]++;
            while(map.size()>k){ key
                if (--map[s[++j]] == 0)  
                map.erase(s[j]);   !!key
            }
            res = max(res, i-j);   j always points one element ahead.
        }
        return res;
    }
};
