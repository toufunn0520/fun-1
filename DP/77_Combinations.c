/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *columnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
void helper(int* input,int n,int k, int** result,int* returnSize,int start, int* tmp, int* cur){
    if((*cur)== k){
        memcpy(result[*returnSize], tmp, sizeof(int)*k);
    /*   
        printf("\nGenerate:\n");
        for(int i =0; i<k; i++){
            printf("\t%d", tmp[i]);
        }
        printf("End\n");
     */
        (*returnSize)++;
        return;
    }
    
    if((*cur)>k) return;

    for(int i = start; i<n; i++){
        tmp[*cur] = input[i]; 
        (*cur)++;
        helper(input, n, k, result, returnSize, i+1, tmp, cur);
        (*cur)--;
    }
}

int** combine(int n, int k, int** columnSizes, int* returnSize) {
    *returnSize = 0;
    if(!n || !k) return NULL;
    int total = 1;
    for(int i = 0; i<k; i++){
        total = total * (n-i)/(i+1);   //KEY!
    }
    total = total;
    //printf("Total =%d\n", total);
    
    int* input = (int*)malloc(sizeof(int) *n); 
    for(int i =0 ; i<n; i++){
        input[i] = i+1;
    }
    int **result = (int**)malloc(sizeof(int*)*total);
    for(int i =0; i< total; i++){
        result[i]=(int*)malloc(sizeof(int)*k);
    }

    int *tmp = (int*)malloc(sizeof(int) *k);
    int cur = 0;
    helper(input,n,k,result,returnSize,0, tmp,&cur);

    *columnSizes = (int*)malloc(sizeof(int)*(*returnSize));
    for(int i = 0; i<*returnSize; i++){
        (*columnSizes)[i] = k;
    }
    
    return result;
}
