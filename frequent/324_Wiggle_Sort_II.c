int comparator(const void *p, const void *q)
{
    // Get the values at given addresses
    int l = *(const int *)p;
    int r = *(const int *)q;
    return (l-r);
}


void wiggleSort(int* nums, int numsSize) {
    
    /*Sort 1st and put small number into odd position, large number into even position and odd start should be mid element*/
    qsort((void*)nums, numsSize, sizeof(int), comparator);
    for(int i = 0; i<numsSize; i++){
       // printf("check i =%d , nums[i] =%d\n", i, nums[i]);
    }
    
    int *tmp = (int*)malloc(sizeof(int)*numsSize);
    int even = numsSize-1;
    int odd = (numsSize-1)/2;  //mid index
    
    for(int i =0; i<numsSize; i++){
        if(i%2 ==0) {
            tmp[i] = nums[odd];
           // printf("Odd check i =%d , tmp[i] =%d, odd=%d\n", i, tmp[i], odd);
            odd--;
        }else{
            tmp[i] = nums[even];
            //printf("Even check i =%d , tmp[i] =%d\n", i, tmp[i]);
            even--;
        }
    }
    
    for(int i = 0; i<numsSize; i++){
        nums[i] = tmp[i];
    }

}
