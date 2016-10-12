/*Need to record both min and max */
int MAX(int p, int q){
    return p>q? p:q;
}

int MIN(int p, int q){
    return p>q? q:p;
}

int maxProduct(int* nums, int numsSize) {
   if(!nums) return 0;

   int *max =(int *)malloc(numsSize*sizeof(int)); 
   int *min =(int *)malloc(numsSize*sizeof(int)); 

   int res = nums[0];
   max[0] = nums[0];
   min[0] = nums[0];
  
   for(int i =1; i<numsSize; i++){
       min[i] = nums[i];
       max[i] = nums[i];
       if(nums[i] > 0){
           max[i] = MAX(max[i], nums[i] * max[i-1]);
           min[i] = MIN(min[i], nums[i]* min[i-1]);
       }else if(nums[i]< 0){
           max[i] = MAX(max[i], nums[i] * min[i-1]);
           min[i] = MIN(min[i], nums[i]*  max[i-1]);
       }
       
       res = MAX(res, max[i]);
       //printf("Update res =%d", res);
       
   }
   return res;
}
