class Solution {
public:
    vector<int> findAnagrams(string s, string p) {
        vector<int>phash(256,0);
        vector<int>shash(256,0);
        vector<int>res;
        res.clear();
        
        if (s.size()<p.size()) return res;
        for(int i = 0; i< p.size(); i++){
            phash[p[i]] ++;
            shash[s[i]] ++;
        }
        if (shash == phash) res.push_back(0);  // interesting.
        //windows
        for(int i = p.size(); i<s.size(); i++){
            shash[s[i]]++;
            shash[s[i-p.size()]]--;
            if(shash == phash) res.push_back(i-p.size()+1);
        }
        return res;
    }
};
