struct NumArray {
    int val;
    int len;
};

/** Initialize your data structure here. */
struct NumArray* NumArrayCreate(int* nums, int numsSize) {
    if(!nums) return NULL;
    struct NumArray* cur = (struct NumArray* )malloc(sizeof(struct NumArray)*numsSize);
    for(int i = 0; i<numsSize; i++){
        cur[i].val = nums[i];
        cur[i].len = numsSize;
    }
    return cur;
}

int sumRange(struct NumArray* numArray, int i, int j) {
    if (i<0) i =0;
    int len =0;
    if (numArray) len = numArray[0].len;
   // printf("len =%d\n", len);
    if (j >= len ) j = len-1;
    int sum = 0;
    for(int tmp = i; tmp<=j; tmp++) sum += numArray[tmp].val;
    return sum;
}

/** Deallocates memory previously allocated for the data structure. */
void NumArrayFree(struct NumArray* numArray) {
    free(numArray);
    numArray= NULL;
}

// Your NumArray object will be instantiated and called as such:
// struct NumArray* numArray = NumArrayCreate(nums, numsSize);
// sumRange(numArray, 0, 1);
// sumRange(numArray, 1, 2);
// NumArrayFree(numArray);
