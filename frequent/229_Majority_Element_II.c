/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* majorityElement(int* nums, int numsSize, int* returnSize) {
    /*Hint the max result is two*/
    *returnSize =0;
    if(!nums) {
        return NULL;
    }
    int *result = (int *)malloc(sizeof(int)*2);
    int count1 =0;
    int target1 = 0;
    int count2 =0;
    int target2=0;
    
    for (int i = 0; i< numsSize; i++){
        if(nums[i]==target1){
            count1 ++;
            continue;
        }
        if(nums[i]==target2){
            count2 ++;
            continue;
        }
        //not a match
        if(count1 ==0){
            count1 ++;
            target1 = nums[i];
         //   printf("New target1 is %d\n", target1);
            continue;
        }
        if(count2 ==0){
            count2 ++;
            target2 = nums[i];
           // printf("New target2 is %d\n", target2);
            continue;
        }       
        // when none of them reached 0;
        count1--;
        count2--;
    }
  //   printf("Summary target1=%d count=%d, target2=%d, count=%d\n", target1, count1, target2, count2);
    // to avoid false value 0:
    if (count1 == 0) {
        count1= -1;
    }else{
        count1 =0;
    }
    if (count2 == 0) {
        count2 = -1;
    }else{
        count2 =0;
    }

    // at max we should return target1 and target2 Let's recaculate the count of those two
    for(int i =0; i< numsSize; i++){
        if(nums[i] == target1 && count1 >=0) count1++;
        if(nums[i] == target2 && count2 >=0) count2++;
  
    }
   //printf("Summary2 target1=%d count=%d, target2=%d, count=%d\n", target1, count1, target2, count2);

    if (count1>numsSize/3) {
        result[*returnSize] = target1 ;
        (*returnSize)++; 
    }
    if (count2>numsSize/3) {
        result[*returnSize] = target2 ;
        (*returnSize)++; 
    }
    
    return result;
}
