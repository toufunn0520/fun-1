class Solution {
public:
    int maxProfit(vector<int>& prices) {
        if (!prices.size()) return 0;
        int mini = INT_MAX;
        int maxi = 0;
        for (int i = 0; i<prices.size(); i++) {
            if( mini<prices[i]) {
                maxi = max(maxi, prices[i]-mini);
            }else {
                mini = prices[i];
            }
        }
        return maxi;
    }
};
