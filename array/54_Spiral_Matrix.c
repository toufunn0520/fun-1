/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* spiralOrder(int** matrix, int matrixRowSize, int matrixColSize) {
    if(!matrix) return NULL;

    int total = matrixRowSize*matrixColSize;
    int* res = (int*)malloc(sizeof(int)*total);
    
    int k= 0;
    int l =0;
    int i =0;
    int m =matrixRowSize;
    int n =matrixColSize;
     /*  k - starting row index
        m - ending row index
        l - starting column index
        n - ending column index
        i - iterator
    */
    
    int index = 0;
    while(k<m && l<n ){
        for(i=l; i<n; i++){
            res[index] = matrix[k][i];
            printf("first: %d ", res[index]);
            index++;
        }
        k++;
        for(i=k; i<m; i++){
            res[index] = matrix[i][n-1];
            printf("second: %d ", res[index]);
            index++;
        }
        n--;
        if(k<m){
          for(i=n-1; i>=l; i--){
            res[index] = matrix[m-1][i];
            printf("third: %d ", res[index]);
            index++;
        }
        m--;
        }
        if(l<n){
          for(i=m-1; i>=k; i--){
            res[index] = matrix[i][l];
            printf("fourth: %d ", res[index]);
            index++;
        }
        l++;
        }
        
    }
  
    return res;

    
}
