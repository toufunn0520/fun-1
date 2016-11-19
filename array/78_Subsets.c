/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *columnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */

void subsetsHelper(int* nums, int numsSize, int** columnSizes, int* returnSize, int* tmp, int**res, int pos, int *len){
    
    if(len >0){
        res[(*returnSize)] = (int*) malloc(sizeof(int)* (*len));
        memcpy( res[(*returnSize)], tmp, sizeof(int)* (*len));
        (*columnSizes)[(*returnSize)] = *len;
   //     printf("The %dth is: \n", *returnSize );
        for(int i = 0; i <(*len); i++){
    //        printf("%d\t", tmp[i]);
        }
      //  printf("\n");
        (*returnSize) ++;
    }
    
    for(int i = pos; i<numsSize; i++){
        tmp[*len] = nums[i];
        (*len) ++;
        subsetsHelper(nums, numsSize, columnSizes, returnSize, tmp, res, i+1, len);
        (*len) --;
    }
      
}

int** subsets(int* nums, int numsSize, int** columnSizes, int* returnSize) {
    /* DFS*/
    *returnSize = 0;
    if(!nums) return NULL;
    
    /*Total Number of combinations*/
    int total = 1;
    for(int i =1; i<=numsSize; i++) total = total *2;
    
    //debug if NECESSARY
  //  printf("We should have %d number of combinations\n", total);
    
    int **res =(int **) malloc(sizeof(int*) * total);
    (*columnSizes) =(int *) malloc(sizeof(int) * total);

    int *tmp = (int*) malloc (sizeof(int)*(numsSize+1));
    int curLen = 0;
    subsetsHelper(nums, numsSize, columnSizes, returnSize, tmp, res, 0, &curLen);

    return res;
}
