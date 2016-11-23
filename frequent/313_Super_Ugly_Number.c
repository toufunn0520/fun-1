    /*
(1) 1×2, 2×2, 3×2, 4×2, 5×2, …
(2) 1×3, 2×3, 3×3, 4×3, 5×3, …
(3) 1×5, 2×5, 3×5, 4×5, 5×5, …
*/

int MIN(int p, int q){
    return p<q? p:q;
}

int nthSuperUglyNumber(int n, int* primes, int primesSize) {
    if(!n || !primes || !primesSize) return 0;
    
    int** result = (int**)malloc(sizeof(int*)*primesSize);
    for(int i =0; i< primesSize; i++){
        result[i] =(int*) malloc(sizeof(int) * (n));
        result[i][0] =1;
    }
    
    int *index = (int*)malloc(sizeof(int)*primesSize);
    for(int i =0; i< primesSize; i++){
        index[i]=0;
    }
    
    int *final =(int*)malloc(sizeof(int)*(n+1));
    final[0] = 1;
    
    for(int i =1; i<n; i++){
        int tmp = INT_MAX;
        int cur = 0;
        
        for(int j =0; j<primesSize; j++){
            tmp = MIN(result[j][index[j]]*primes[j], tmp);
            if(tmp == result[j][index[j]]*primes[j]) {
                cur = j;
            }
        }
        //key aviod same results
         for(int j =0; j<primesSize; j++){
             if(tmp == result[j][index[j]]*primes[j]){
                 final[i] = tmp;
                 index[j] =index[j]+1;
            //key
            result[j][index[j]] = final[index[j]];
             }
         }
        
     //   printf("Bingo %d index =%d latest = %d ++++++++++ The %dth  final=%d\n", primes[cur], index[cur],  final[index[cur]], i, final[i]);
    }
    return final[n-1];
    
}
