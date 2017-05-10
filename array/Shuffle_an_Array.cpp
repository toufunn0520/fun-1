class Solution {
public:
    vector<int> nums;
    Solution(vector<int> nums) {
        this->nums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    vector<int> reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    vector<int> shuffle() {
        vector<int> res(nums);
        for (int i = nums.size()-1; i>0; i--) {
            int index = rand()%(i+1);
            swap(res[index] ,res[i]);
        }
        return res;
    }
};
