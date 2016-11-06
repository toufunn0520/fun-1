int majorityElement(int* nums, int numsSize) {
    if (!nums) return 0;
    int count =0 ;
    int target;
    /*抵消*/
    for (int i =0; i<numsSize; i++){
       if (count == 0) {
           target = nums[i];
           count =1;
       }else{
           if(target != nums[i]) {
               count--;
           }else{
               count++;
           }
       }
    }
    return target;
}
