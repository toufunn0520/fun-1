/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
 
// This function is used in qsort to decide the relative order
// of elements at addresses p and q

int MAX(int p, int q){
    return p>q?p:q;
}

int comparator(const void *p, const void *q){
    
    // Get the values at given addresses
    int l = *(const int *)p;
    int r = *(const int *)q;
 
    // both odd, put the greater of two first.
    return (l-r);
}

int* largestDivisibleSubset(int* nums, int numsSize, int* returnSize) {
    /*DP sorted array 若a<b且b%a==0 ,  b <c 且 c%b==0那么必然有c%a==0*/
    *returnSize =0;
    if(!nums || numsSize ==0 ) return NULL;
    int* res =(int *) malloc (sizeof(int) * (numsSize+1));
    int* tmp =(int *) malloc(sizeof(int) * (numsSize+1));  // save the index of next jumps
    int* dp =(int *) malloc(sizeof(int) * (numsSize+1));

    int max_dp =0;
    int max_index = 0 ;
    qsort((void*)nums, numsSize, sizeof(int), comparator);

    for(int i = 0; i< numsSize; i++){
 //       printf("%dth is %d\n", i, nums[i]);
        dp[i] =1;
        tmp[i] = -1;
    }
    // sorted as an acending order
    for(int i =0; i< numsSize; i++){
        for(int j =0; j< i; j++){
            if( nums[i] % nums[j] == 0){
                if(dp[j]+1 > dp[i]){
                    tmp[i] = j;
                    dp[i] = dp[j]+1;
                } 
            }
        }
//        printf(" %dth is %d nextjump =%d\n", i , dp[i], tmp[i]);
        if(max_dp < dp[i])  {
            max_index = i;
            max_dp = dp[i];
        }
       
    }
   // printf("OK, max_index =%d max_dp=%d\n", max_index, max_dp);
    // Put the max result into res/returnSize
    for (int i = max_index; i>=0; i= tmp[i]){
        res[*returnSize] = nums[i];
       // printf("%dth result =%d\n", *returnSize, res[*returnSize]);
        (*returnSize)++;
    }
    return res;
}
