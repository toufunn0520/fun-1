class Solution {
public:
    int firstUniqChar(string s) {
        vector<int>hash(26, -1);
        for(int i =0; i<s.size(); i++){
            if(hash[int(s[i]-'a')] == -1){
                hash[int(s[i]-'a')] = i;
            }else {
                hash[int(s[i]-'a')] = -2;
            }
        }
        
        int min = s.size();
        for(int i =0; i<hash.size(); i++){
            if(min>hash[i] && hash[i] >=0 ) min = hash[i];
        }
        
        if(min == s.size()) return -1;
        return min;
    }
};
class Solution {
public:
    int firstUniqChar(string s) {
        unordered_map<char, int> m;
        for(auto &c : s) {
            m[c]++;
        }
        for(int i = 0; i < s.size(); i++) {
            if(m[s[i]] == 1) return i;
        }
        return -1;
    }
};
if the string is extremely long, we wouldn't want to traverse it twice
public:
    int firstUniqChar(string s) {
        unordered_map<char, pair<int, int>> m;
        int idx = s.size();
        for(int i = 0; i < s.size(); i++) {
            m[s[i]].first++;
            m[s[i]].second = i;
        }
        for(auto &p : m) {
            if(p.second.first == 1) idx = min(idx, p.second.second);
        }
        return idx == s.size() ? -1 : idx;
    }
};
