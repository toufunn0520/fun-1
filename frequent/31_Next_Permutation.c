void swap(int *a, int *b){
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

int *reverse(int* nums, int start, int end, int size){
    if(!nums || size <=0 || end -start<=0) return nums;
    int *result = (int*)malloc(sizeof(int)*size);
 
    for(int i =0; i<size; i++){
        if(i<start){result[i] = nums[i]; continue;}
        if(i>end) {result[i] = nums[i]; continue;}
        result[i] = nums[end-i+start];
     //   printf("swap happened for %d %d\n",i, end-i+start);
    }

    memcpy(nums, result, sizeof(int)*size);
    for(int i =0; i<size; i++){
      //   printf("Finial result %d=%d \n",i, nums[i]);
     }
    return nums;
}

/*从后往前找到第一个升序对的位置*/
/*调整大小排列顺序的可能，从后往前找到比当前位置大的元素，交换之*/
/*当前位置后面的元素还是降序排列，将他们反转得到最小顺序排列。其实就是原来当前位置元素后面是最大的排列，而交换后的新元素之后是最小的排列，他们就是相邻的顺序*/
/*当不存在升序，则当前排列是最大排列，只要旋转整个序列变成最小排列。*/
void nextPermutation(int* nums, int numsSize) {
    //1st 
    for(int i = numsSize-2; i>=0; i--){
        if(nums[i] >=nums[i+1]) continue;
        int j =0;
        printf("Found the 1st=%d\n", nums[i]);
        for( j=numsSize-1;j>i-1;--j) if(nums[j]>nums[i])break;  
        printf("nums[i]=%d, nums[j] =%d i=%d j=%d\n", nums[i], nums[j], i, j);
        swap(&nums[i],&nums[j]); 
        printf("nums[i]=%d, nums[j] =%d\n", nums[i], nums[j]);
        printf("About to reverse start =%d end=%d\n", i+1, numsSize-1);
        return reverse(nums, i+1, numsSize-1, numsSize);  
    }
    
    return reverse(nums, 0, numsSize-1,numsSize);
    
}
