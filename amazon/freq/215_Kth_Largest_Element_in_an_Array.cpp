class Solution {
public:
    int helper(vector<int>& nums,int start, int end) {
        int pivot = nums[start];
        int l = start+1;
        int r = end;
        while (l <= r) {  // need to cross
            if (nums[l]<pivot && nums[r]>pivot) {
                swap(nums[l++], nums[r--]);
            }else if(nums[l]>=pivot){
                l++;
            }else if(nums[r]<=pivot){
                r--;
            }
        }
        swap(nums[start],nums[r]); //key find pivot location r must point on larger one than pivot
        return r;
    }
    int findKthLargest(vector<int>& nums, int k) {
        // use quickselect
        int cur = -1;
        int start = 0;
        int end = nums.size()-1;
        while (true) {                      // key
            cur = helper(nums, start, end);  //based on index narr
            if (cur == k-1) break;
            if (cur < k-1) {
                start = cur + 1;
            }else{
                end = cur -1;
            }
        }
        return nums[k-1];
    }
};
