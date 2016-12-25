/**
 * Return an array of arrays.
 * Note: The returned array must be malloced, assume caller calls free().
 */
int** multiply(int** A, int ARowSize, int AColSize, int** B, int BRowSize, int BColSize) {
    
    if(!A || !B || AColSize !=BRowSize) return NULL;
    int **C = (int**)malloc(sizeof(int*)*ARowSize);
    
    for(int i =0; i<ARowSize; i++){
        C[i] = (int*)malloc(sizeof(int)*BColSize);
        memset(C[i], 0,sizeof(int)*BColSize);
    }
    for(int i = 0;i<ARowSize; i++){
        for(int k=0; k<AColSize; k++){
            if(A[i][k]!=0){
               for(int j=0; j<BColSize; j++){
                 if (B[k][j] != 0) C[i][j] += A[i][k] * B[k][j];               } 
            }
        }
    }
    return C;
}
