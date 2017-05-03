class Solution {
public:
    vector<int> productExceptSelf(vector<int>& nums) {
       int N = nums.size();
        vector<int> res(N,1);
        int left = 1;
        for(int i=0;i<nums.size(); i++){
            res[i] = left;
            left *= nums[i];
        }
        int right = 1;
        for(int j =nums.size()-1; j>=0; j--){
            res[j] *= right;
            right *= nums[j];
        }
        return res;
    }
};
