int compare(const void* p, const void* q){
    int l = *(const int*)p;
    int r = *(const int*)q;
    if(l<r) return -1;
    if(l==r) return 0;
    return 1;

}
int MAX(int p, int q){
    return p>q?p:q;
}

int longestConsecutive(int* nums, int numsSize) {
    if(!nums || !numsSize) return 0;
    int max =1;
    int max_tmp =1;
    //sorted! The complexity reaches nlogn
    qsort((void*)nums, numsSize, sizeof(int), compare);
    for(int i =0; i<numsSize; i++){
       // printf("i=%d nums=%d", i, nums[i]);
    }
    for(int i =1;i<numsSize; i++){
        /* Update */
        if(nums[i] == nums[i-1]+1 ) {
            max_tmp++;
        //    printf("Bingo\n");
        }else if(nums[i] == nums[i-1]){
            // do nothing
        }else{
            max = MAX(max, max_tmp);
            max_tmp =1;
        }
    }
    max = MAX(max, max_tmp);// key the last updates. Since every updates happen once breaker encountered
    return max;
}
