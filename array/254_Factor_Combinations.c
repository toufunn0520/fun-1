/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *columnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
 
 void helper(int** res,int** columnSizes,int* returnSize, int n, int* tmp, int* cur, int start){
     if( n<=1 && (*cur > 1) ){
        // printf("Bingo cur=%d\n", *cur);
         res[*returnSize] = (int*)malloc(sizeof(int)*(*cur));
         memcpy(res[*returnSize], tmp, sizeof(int)*(*cur));
         (*columnSizes)[*returnSize] =*cur;
         (*returnSize)++;
     }
     
     for(int i =start; i<=n; i++){
         if(n%i ==0){
             tmp[*cur] = i;
             (*cur)++;
             helper(res, columnSizes, returnSize, n/i, tmp,cur, i);
             (*cur)--;
         }
     }
 }
 
int** getFactors(int n, int** columnSizes, int* returnSize) {
    
    *returnSize =0;
    if(n<=1) return NULL;
    int** res = (int**)malloc(sizeof(int*)*200);
    *columnSizes = (int*)malloc(sizeof(int)*200);
    int *tmp = (int*)malloc(sizeof(int)*100);
    
    int cur =0;
    helper(res, columnSizes, returnSize, n, tmp, &cur, 2);
    
    return res;
}
