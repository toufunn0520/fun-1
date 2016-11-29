/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *columnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
 
 // c preallocation 容易overflow！ 所以要走两次
int compare(const void* p, const void* q){
    
    int l = *(const int *)p;
    int r = *(const int *)q;
    return l-r;

}
void helper_size(int *candidates, int candidatesSize, int target, int *returnSize, int start, int *tmp, int *cur){
    if(target == 0){
        (*returnSize)++;
    }
        
    //在扫描candidates[i]时，对candidate[i: n-1]递归查找target-candidates[i]。
    for(int i =start; i<candidatesSize;i++){
        if(i>start && candidates[i]==candidates[i-1]) continue;  // key
        if(target>=candidates[i]){
            tmp[*cur] = candidates[i];
            (*cur)++;
            helper_size(candidates, candidatesSize, (target-candidates[i]), returnSize, i, tmp, cur);
            (*cur)--;
        }
    }
}

void helper(int** result, int * candidates, int candidatesSize, int target, int** columnSizes, int *returnSize, int start, int* tmp, int*cur){
    if(target == 0){
        memcpy(result[*returnSize], tmp, sizeof(int)*((*cur)));
        (*columnSizes)[*returnSize] = *cur;
        (*returnSize)++;
   /*     printf("\nGenerate the %dth: size =%d target =%d \n", *returnSize, *cur, target);
        for(int j =0; j<*cur; j++ ){
            printf("%d\t", tmp[j]);
        }
     */
        return;
    }
        
    
    /*在扫描candidates[i]时，对candidate[i: n-1]递归查找target-candidates[i]。*/
    for(int i =start; i<candidatesSize;i++){
        if(i>start && candidates[i]==candidates[i-1]) continue;  // key
        if(target>=candidates[i]){
            tmp[*cur] = candidates[i];
            (*cur)++;
            helper(result, candidates, candidatesSize, (target-candidates[i]), columnSizes, returnSize, i, tmp, cur);
            (*cur)--;
        }
    }
    return;
}

int** combinationSum(int* candidates, int candidatesSize, int target, int** columnSizes, int* returnSize) {
    *returnSize= 0;
    if(!candidates) return NULL;
    
    // sorted
    qsort((void*)candidates, candidatesSize, sizeof(int), compare);
    
    int *tmpp = (int*)malloc(sizeof(int)*(candidatesSize*18));
    int  curr = 0;
    helper_size(candidates, candidatesSize, target, returnSize, 0, tmpp, &curr);
    if(*returnSize ==0) {
        free(tmpp);
        tmpp = NULL;
        return NULL;
    }
    
    int ** result = (int**)malloc(sizeof(int*)*(*returnSize));
    *columnSizes = (int*)malloc(sizeof(int)*(*returnSize));
    for(int i = 0; i<(*returnSize); i++){
        result[i] = (int*)malloc(sizeof(int)*(candidatesSize*18));
    }
    int *tmp = (int*)malloc(sizeof(int)*(candidatesSize*18));
    int  cur = 0;
    *returnSize =0;
    helper(result, candidates, candidatesSize, target, columnSizes, returnSize, 0, tmp, &cur);
    
    free(tmp);
    tmp = NULL;
    return result;
}
