class Solution {
public:
    string frequencySort(string s) {
        unordered_map<char, int> freq;
        map<int, string>sub;
        for(char c: s){
            freq[c]++;
        }
       
        for(auto &it:freq){
            char c = it.first;
            int n = it.second;
            sub[n] += string(n, c);
        }
        string res;
        /*map is ordered by key*/
        for(auto rit = sub.rbegin(); rit != sub.rend(); ++rit) {
            res += rit->second;
        }
        return res;
    }
};
