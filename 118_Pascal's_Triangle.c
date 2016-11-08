/**
 * Return an array of arrays.
 * The sizes of the arrays are returned as *columnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
int** generate(int numRows, int** columnSizes) {
    int ** result = (int**)malloc(sizeof(int*)*numRows);
    *columnSizes = (int*)malloc(sizeof(int)*numRows);

    for(int i=0; i<numRows; i++){
        result[i] = (int*)malloc(sizeof(int)*(i+1));
        (*columnSizes)[i] = i+1;
        result[i][i] = 1;
        result[i][0] = 1;
        for(int j =1; j<=i-1; j++){
            result[i][j] = result[i-1][j-1]+ result[i-1][j];
        }
        
    }
    return result;
}
