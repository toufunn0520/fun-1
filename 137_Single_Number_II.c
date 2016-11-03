int singleNumber(int* nums, int numsSize) {
    int count[32] ={0};
    int result = 0;
    for(int i =0; i< 32; i++){
        for(int j =0; j < numsSize; j++){
            count[i] += (nums[j]>> i &1);
        }
        result |= (count[i] %3 << i);
    }
    return result;
}
