/*Binary search in 2D  use diagoal as the base*/

bool searchMatrix(int** matrix, int matrixRowSize, int matrixColSize, int target) {
    if(!matrix) return false;
    int i = matrixRowSize-1;
    int j = 0;
    while(j<matrixColSize && i>=0){
        if(matrix[i][j] == target){
            return true;
        }else if(matrix[i][j] < target){
            j++;
        }else {
            i--;
        }
    }
    
    return false;
}
