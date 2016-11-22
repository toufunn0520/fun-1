/**
 * Return an array of arrays.
 * Note: The returned array must be malloced, assume caller calls free().
 */
int** generateMatrix(int n) {
    int ** res = (int**)malloc(sizeof(int*)*n);
    for(int i =0; i<n;i++){
        res[i] = (int*)malloc(sizeof(int)*(n));
    }
    int l =0; int k =0; int i =0; int m =n;
    int val =1;
    /*Generate the result*/
    /*  k - starting row index
        m - ending row index
        l - starting column index
        n - ending column index
        i - iterator
    */
    while(k<m &&l<n){
        for(i =l; i<n ; i++){
            res[k][i] = val;
           // printf("first: %d i =%d k=%d", val, i,k);
            val++;
        }
        k++;
        for(i =k; i<m; i++){
            res[i][n-1] = val;
          //  printf("second: %d ", val);
            val++;
        }
        n--;
        if(k<m){
            for(i= n-1; i>=l; i--){
                res[m-1][i] =val ;
             //   printf("Third: %d ", val);
                val++;
            }
            m--;
        }
        if(l<n){
            for(i =m-1; i>=k; i--){
                res[i][l] =val ;
              //  printf("Fourth: %d ", val);
                val++;
             }
            l++;
        }
        
    }
    
    return res;
}
