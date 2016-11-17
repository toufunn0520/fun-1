struct NumMatrix {
    int **sum;
    int row;
    int col;
};

/** Initialize your data structure here. */
struct NumMatrix* NumMatrixCreate(int** matrix, int matrixRowSize, int matrixColSize) {
    if(!matrix) return NULL;
    struct NumMatrix* head = (struct NumMatrix*)malloc(sizeof(struct NumMatrix));
    head->sum = (int **)malloc(sizeof(int*)*matrixRowSize);
    for(int i =0; i<matrixRowSize; i++){
        (head->sum)[i] = (int*)malloc(sizeof(int) *matrixColSize);
    }
    /*Generate sum[i][j] - sum of matrix[0][0] till matrix[i][j] */
    for(int i = 0; i<matrixRowSize; i++){
        int curRow= 0;
        for(int j = 0; j<matrixColSize; j++){
            (head->sum)[i][j] = 0;
            if(i ==0) {
                (head->sum)[i][j] += matrix[i][j] + curRow;
            }else{
                (head->sum)[i][j] += matrix[i][j] + (head->sum)[i-1][j]+curRow;
            }
            curRow += matrix[i][j];
            //printf("CHeck i=%d j=%d sum =%d\n", i, j, (head->sum)[i][j]);
        }
    }
    /*If debug is needed*/
    
    head->row = matrixRowSize;
    head->col = matrixColSize;
    return head;
}

int sumRegion(struct NumMatrix* numMatrix, int row1, int col1, int row2, int col2) {
    if(!numMatrix) return 0;
    int **sum = numMatrix->sum;
    int row = numMatrix->row;
    int col = numMatrix->col;
    if( row1 >= row || row2>= row || col1 >= col || col2 >= col) return 0;
    int res = sum[row2][col2];
    if(row1>0) res -=  sum[row1-1][col2];
    if(col1>0) res -= sum[row2][col1-1];
    if(row1 >0 && col1>0 ) res += sum[row1-1][col1-1];
   // printf("Res = %d start =%d sub1= %d sub2=%d add1=%d\n", res,sum[row2][col2],  sum[row1-1][col2], sum[row2][col1-1],sum[row1-1][col1-1] );
    return res; 
    
}

/** Deallocates memory previously allocated for the data structure. */
void NumMatrixFree(struct NumMatrix* numMatrix) {
    if(!numMatrix) return;
    for(int i =0; i<(numMatrix->row); i++){
        free((numMatrix->sum)[i]);
        (numMatrix->sum)[i] = NULL;

    }
    free(numMatrix->sum);
    numMatrix->sum =NULL;
    free(numMatrix);
    numMatrix = NULL;
    return;
}

// Your NumMatrix object will be instantiated and called as such:
// struct NumMatrix* numMatrix = NumMatrixCreate(matrix, matrixRowSize, matrixColSize);
// sumRegion(numMatrix, 0, 1, 2, 3);
// sumRegion(numMatrix, 1, 2, 3, 4);
// NumMatrixFree(numMatrix);
