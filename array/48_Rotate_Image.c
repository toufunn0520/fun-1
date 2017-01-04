void rotate(int** matrix, int matrixRowSize, int matrixColSize) {
    if(!matrix && matrixRowSize!=matrixColSize) return matrix;
    int len = matrixRowSize;
    // 1st reverse up to downs
    for(int i =0; i<len/2; i++){
        int *tmp = matrix[i];
        matrix[i] = matrix[len-1-i];
        matrix[len-i-1] = tmp;
    }
    //2nd swap symmetry
    for(int i =0; i<len; i++){
        for(int j =i+1; j<len; j++){
            int tmp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = tmp;
        }
    }
    return matrix;
}
