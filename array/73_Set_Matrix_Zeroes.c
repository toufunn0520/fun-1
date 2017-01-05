void setZeroes(int** matrix, int matrixRowSize, int matrixColSize) {
    if(!matrix || (!matrixRowSize && !matrixColSize)) return matrix;
    
    int col0 = 1;
    int row = matrixRowSize;
    int col = matrixColSize;
    
    for(int i =0; i<row;i++){
        if(matrix[i][0]==0) col0 =0;
        for(int j = 1; j<col; j++){
            if(matrix[i][j] == 0) matrix[0][j] = matrix[i][0] = 0;
        }
    }
    
    for(int i =row-1; i>=0; i--){
        for(int j = col-1; j>=1; j--){
            if(matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] =0;
        }
        if(col0 ==0) matrix[i][0] = 0;
    }
    return matrix;
}
