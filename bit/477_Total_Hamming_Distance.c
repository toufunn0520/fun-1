/*Start from each bit e.g the ith bits have k 1 and n-k 0*/

int totalHammingDistance(int* nums, int numsSize) {
    if(!nums || numsSize<=0) return 0;
    int res = 0;
    int count[32] = {0};
    for(int i =0; i< numsSize; i++){
        int cur = nums[i];
        int j =0;
        while(cur){
            count[j] += cur&1;
            cur = cur>>1;
            j++;
        }
    }
    for(int i =0; i<32; i++){
        if(count[i]>0){
           //printf ("The %dth bit 1 is %d\n", i, count[i]);
           res += count[i]*(numsSize-count[i]);
        }
    }
    return res;
}
