/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
 
 /*1. 只要"("的数量没有超过n，都可以插入"("。
2. 而可以插入")"的前提则是当前的"("数量必须要多余当前的")"数量。
*/
 void  helper(int n,char** result,char* tmp,int left,int right, int* returnSize, int* index){
     if(left ==0 && right ==0){
        result[*returnSize] = (char*)malloc(sizeof(char)* (2*n+1)) ;
        memcpy(result[*returnSize], tmp, 2*n+1);
   //     printf("Generate the %dth parenthesis=%s\n", *returnSize,result[*returnSize] );
        (*returnSize) ++;
     }
    if (left > 0) {
          tmp[(*index)] = '(';
          (*index)++;
		  helper(n, result, tmp, left - 1, right, returnSize, index);
		  (*index)--;
    }
        
    if (right > 0 && left < right) {
           tmp[(*index)] = ')';
           (*index)++;
		   helper(n,result, tmp, left, right - 1, returnSize, index);
		   (*index)--;

    }
     
 }

char** generateParenthesis(int n, int* returnSize) {
    *returnSize = 0;
    if(!n) return NULL;
    int totallen = 0;
    int sum =0;
    if(n<=2) totallen =n;
    if(n==3) totallen =5;
    if (n>3) sum = 7;
    for(int i =4; i<=n;i++){
        sum += totallen;
        totallen = sum*2;
    }
    
    char** result = (char**)malloc(sizeof(char*) * (totallen*2));
    char* tmp = (char*)malloc(sizeof(char) * (2*n+1));
    tmp[2*n] = '\0';
    int index =0;
    helper(n,result,tmp,n,n, returnSize,&index );
    return result;
    
}
