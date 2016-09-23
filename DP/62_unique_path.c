/*Climbing stairs DP
    dp[i][j] = dp[i-1][j] + dp[i][j-1]
*/

int uniquePaths(int m, int n) {
    int **dp = (int**)malloc(m*sizeof(int*));
    
    for(int i =0; i<m; i++){
        dp[i] = (int*)malloc(n*sizeof(int));
        dp[i][0] = 1;
    }
    
    for(int j =0; j<n; j++){
        dp[0][j] = 1;
    }
    for(int i =1; i<m; i++){
        for(int j=1; j<n; j++){
            dp[i][j] = dp[i-1][j] + dp[i][j-1];
            //printf("i=%d, j=%d dp[i][j]=%d\n", i, j, dp[i][j]);
        }
    }
    
    return dp[m-1][n-1];
}
