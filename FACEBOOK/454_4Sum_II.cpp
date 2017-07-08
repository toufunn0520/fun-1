/* Devide and Conquer split into pairs with two element
 * And use combinations to get final answers
 */

class Solution {
public:
    
    void update(vector<int>& A, vector<int>& B, unordered_map<int, int>& m) {
        for (int i = 0 ; i<A.size(); i++) {
            for (int j = 0 ; j<B.size(); j++) {
                m[A[i]+B[j]]++;
            }
        }
    }
    
    int fourSumCount(vector<int>& A, vector<int>& B, vector<int>& C, vector<int>& D) {
        
        unordered_map<int, int> m1, m2;
        update(A, B, m1);
        update(C, D, m2);
        int res = 0;
        
        for (auto ita = m1.begin(); ita!=m1.end(); ita++) {
            auto itb = m2.find(-(ita->first));
            if (itb != m2.end()) {
                res += (itb->second)*(ita->second); // combinations
            } 
        }
        return res;
    }
    
};
