/*
(1) 1×2, 2×2, 3×2, 4×2, 5×2, …
(2) 1×3, 2×3, 3×3, 4×3, 5×3, …
(3) 1×5, 2×5, 3×5, 4×5, 5×5, …
*/


int MIN(int p,int q){
    return p<q? p:q;
}

int nthUglyNumber(int n) {
 
    if(!n) return 0;
    int **result = (int*)malloc(sizeof(int)*3);
    for(int i =0; i<3; i++){
        result[i] = (int*)malloc(sizeof(int)*n);
    }
    int *final = (int*)malloc(sizeof(int)*(n+2));

    int i=0;int j=0;int k =0;

    result[0][0] = 1;
    result[1][0] = 1;
    result[2][0] = 1;
    final[0] =1;

    for(int index =1; index<n; index++){
 
        final[index] = MIN(MIN(result[0][i]*2, result[1][j]*3), result[2][k]*5);

        if(final[index] == result[0][i]*2) {
            i++;
            result[0][i] = final[i];
     //       printf("Binge 2!!! %dth result=%d\n", i, result[0][i]);
        }
        if(final[index] == result[1][j]*3){
            j++;
            result[1][j] = final[j];
    //        printf("Binge 3!!! %dth result=%d\n", j, result[0][j]);

        }
        if(final[index] == result[2][k]*5) {
            k++;
            result[2][k] = final[k];
    //        printf("Binge 5!!! %dth result=%d\n", k, result[0][k]);

        }
        
       // printf("The %dth final=%d\n", index, final[index]);
    }
    return final[n-1];
} 
