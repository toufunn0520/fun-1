int thirdMax(int* nums, int numsSize) {
    if( !nums||!numsSize) return 0;
    int res[3] = {INT_MIN+1,INT_MIN+1,INT_MIN+1};
    for(int i =0; i<numsSize; i++){
        if(nums[i] == res[2] || nums[i] == res[1] || nums[i] == res[0]) continue;
        if (nums[i]>res[2] || (res[2] ==INT_MIN+1) ) {
            res[0] = res[1];
            res[1] = res[2];
            res[2] = nums[i];
        }else if((nums[i]>res[1]) || ((res[1]== INT_MIN+1))) {
            res[0] = res[1];
            res[1] = nums[i];
        }else if((nums[i]>res[0]) || ((res[0] == INT_MIN+1))){
            res[0] = nums[i];
        }
        //printf("shit %d  res[0]=%d res[1]=%d res[2]=%d\n",i, res[0], res[1], res[2]);

    }
    return (res[0]==INT_MIN+1)?res[2]:res[0];
    
}
