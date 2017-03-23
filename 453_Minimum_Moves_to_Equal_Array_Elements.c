/*Math*/
int MIN(int p, int q){
    return p<q?p:q;
}
int minMoves(int* nums, int numsSize) {
    if (!nums || numsSize <=1) return 0;
    int sum = 0;
    int min = nums[0];
    
    for (int i = 0; i <numsSize; i++) {
        min = MIN(min, nums[i]);
        sum += nums[i];
    }
    return sum-(numsSize*min);
}
