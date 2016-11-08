void moveZeroes(int* nums, int numsSize) {
    int j =0;
    for (int i = 0; i<numsSize; i++){
        if(nums[i] == 0){
            j =i+1;
            while(j<numsSize){
                if(nums[j]) {
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                    break;
                }
                j++;
            }
         }
    }
    return ;
}
