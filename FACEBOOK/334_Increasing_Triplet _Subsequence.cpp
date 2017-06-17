/*filling from Smallest to second small， till we are about to fill the third one return true*/
class Solution {
public:
    bool increasingTriplet(vector<int>& nums) {
        int len = nums.size();
        int smallest = INT_MAX;
        int second = INT_MAX;
        for (int i = 0; i <len; i++) {
            if (nums[i] <= smallest) {
                smallest = nums[i];
            }else if(nums[i] <= second){
                second = nums[i];
            }else{
                /*find the 3rd smallest*/
                return true;
            }
        }
        return false;
    }
};
