class Solution {
public:
    string minWindow(string s, string t) {
        
        if(t.size()>s.size()) return "";
        unordered_map<char, int> count;
        count.clear();
        for (char c: t) {
            count[c]++;
        }
        
        int head = 0, start = 0, end = 0;
        int dd = INT_MAX;
        //(small is good)
        int match = 0;
        while (end < s.size()) {
            // try find all matches
            if (count[s[end++]]-->0) {   //move the 2nd pointer
                match ++;
            }
            while (match == t.size() && start <= end) {
                if (end-start < dd){
                    dd = end-start;      // update min len
                    head = start;
                }
                if (count[s[start++]]++ == 0){   // move the 1st pointer
                    match --;
                }
            }
        }
        return dd==INT_MAX? "":s.substr(head,dd);
        
    }
};


//2nd
class Solution {
public:
    string minWindow(string s, string t) {
        unordered_map<char, int> map;
        int match = 0;
        match = t.size();
        
        for (char c: t) map[c]++;  
        int start = 0;
        int end = 0;
        int head  = 0;
        int res = INT_MAX;
        
        while (end<s.size()) {
            if (map[s[end++]]-- > 0) {
                match--;
            }
            while (match == 0 && start <= end) {
                if (res > end-start) {
                    head = start;
                    res = end-start;
                }
                if (map[s[start++]]++ == 0) {
                    match++;
                }
            }
        }
        return res == INT_MAX? "":s.substr(head,res);
    }
};
