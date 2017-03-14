void swap(int *p, int *q){
    int tmp = *p;
    *p=*q;
    *q=tmp;
}
void wiggleSort(int* nums, int numsSize) {
    if(!nums || !numsSize) return;
    for (int i =0; i< numsSize; i++) {
        if(i%2){
            if (nums[i-1] >nums[i]) swap(&nums[i-1], &nums[i]);
        }else{
            if(i!=0 && nums[i-1]<nums[i]) swap(&nums[i-1], &nums[i]);
        }
    }
    return;
}
