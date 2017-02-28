/*SO SMART*/
int findDuplicate(int* nums, int numsSize) {
    if(!nums || numsSize <=1) return -1;
    int fast = nums[nums[0]];
    int slow = nums[0];
    while(fast !=slow){
        fast = nums[nums[fast]];
        slow = nums[slow];
    }
    fast =0;
    while(fast != slow ){
        fast = nums[fast];
        slow = nums[slow];
    }
    return slow;
}
