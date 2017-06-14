class Solution {
public:
    bool isOneEditDistance(string s, string t) {
        int m = s.size();
        int n = t.size();
        /*Since we are likely to do subtrace and add 
         *We can further simplified by doing only add and make sure s<t
         */
        bool mismatch = false;
        if (m>n) return isOneEditDistance(t,s);
        for (int i = 0; i<m; i++) {
            if (s[i] != t[i]) {
               if (n==m) {
                   s[i] = t[i];
               }else{
                   s.insert(i,1,t[i]);
               }
               mismatch = true;
               break;
            }
        }
        /*the 1st condtion says 'ab' vs 'abc'  the second covers samelength one diff and one insertion in the middle*/
        return ( (!mismatch && (n-m==1)) || (mismatch && t==s));
        
    }
};
