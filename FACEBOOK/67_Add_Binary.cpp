class Solution {
public:
    string addBinary(string a, string b) {
        string res = "";
        int c = 0;
        int ai = a.size()-1;
        int bi = b.size()-1;
        
        while (ai>=0 || bi>=0 || c>0 ) {   // key c is bigger than 0 which means, where c is like a carrier in binary calculation
            if (ai>=0) c += a[ai--] -'0'; 
            if (bi>=0) c += b[bi--] -'0';
            res = char(c%2+'0') + res;
            c = c/2;
        }
        
        return res;
        
    }
};
