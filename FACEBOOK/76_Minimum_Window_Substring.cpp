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
