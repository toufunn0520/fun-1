int compare(const void* p, const void* q){
    int l = *(int*)p;
    int r = *(int*)q;
    return l-r;
}
int threeSumSmaller(int* nums, int numsSize, int target) {
    /*Sort array*/
    if(!nums || (numsSize <3)) return 0;
    int count = 0;
    
    qsort((void*)(nums), numsSize, sizeof(int), compare);

    for (int i =0; i<numsSize-2; i++) {
        int left = i+1; 
        int right = numsSize-1;
        while(left<right){
            if((nums[i] + nums[left]+nums[right]) < target){
                count += right-left;  //key (left+1,right) all satisfied.
                left++;
            }else{
                right--;
            }
        }
        
    }
    return count;
}
