class Solution {
public:
    int numDecodings(string s) {
        int len = s.size();
        if (len == 0) return 0;
        int res[len+1] = {0};
        
        res[len] = 1;
        res[len-1] = (s[len-1]=='0')? 0:1;    
        
        for (int i = len-2; i>=0; i--) {
            if (s[i] == '0') continue;
            if (stoi(s.substr(i, 2)) <=26) {
                res[i] = res[i+1]+res[i+2];
                
            } else {
                res[i] = res[i+1];
            }
        }
        return res[0];
    }
};
