class Solution {
public:
    string helper(string s, int& i) {
        string res = "";
        int n = s.size();
        /*curlevel character*/
        while (i<n &&  s[i] !=']') {
            if (!isdigit(s[i])) { 
                res += s[i];
                i++;
            } else {
                int count = 0;
                /*Prep for next level count*/
                while(isdigit(s[i]) && i<n){
                    count = count*10+ (s[i]-'0');  // don't be silly
                    i++;
                }
                i++; // skip [
                string next = helper(s,i);
                i++; //skp ]
                while(count>0) {
                    res += next;
                    count--;
                }
            }
        }
        return res;
    }
    
    string decodeString(string s) {
        int index = 0;
        return helper(s, index);
    }
};
