class Solution {
public:
    string longestPalindrome(string s) {
        if (!s.size()) return s;
        string res="";
        int longest = s.size();
        for (int len = longest; len >=1; len--) {
            for (int start = 0; start<=longest-len; start++) {
                int left = start;
                int right = start+len-1;
                
                bool match = true;
                while(left < right){
                    if(s[left]!= s[right]){
                        match = false;
                        break;
                    }
                    left++;
                    right--;
                    if(!match) break;
                }
                if(match) {
                    res=s.substr (start, len);
                }
            }
            if(res !="") break;
        }
        return res;
    }
};
