int MIN (int p, int q){
    return p<q?p:q;
}

int numSquares(int n) {
    int *result = (int*) malloc(sizeof(int)*(n+1));
    result[0] = 0;
    for(int i =1;i<=n; i++){
        int tmp = n;
        for(int j =1; j*j<=i; j++){
            tmp = MIN(tmp,  result[i-j*j]+1);
        }
        result[i] = tmp;
    }
    return result[n];
}
