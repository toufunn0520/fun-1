/* Unique Path
 *
 */

int uniquePathsWithObstacles(int** obstacleGrid, int obstacleGridRowSize, int obstacleGridColSize) {
    
    int m = obstacleGridRowSize;
    int n = obstacleGridColSize;
    if(!obstacleGrid) return 0;
    int **dp = (int**)malloc(m*sizeof(int*));

    for(int i =0; i<m; i++){
        dp[i] = (int*)malloc(n*sizeof(int));
    }
        
   // printf("HAHA\n");
    if(obstacleGrid[0][0]==1) {
        dp[0][0] = 0;
    }else{
        dp[0][0] = 1;
    }
    
    for(int i =1; i<m; i++){
        if(obstacleGrid[i][0]!=1){
            dp[i][0] = dp[i-1][0];
        }else{
            dp[i][0] = 0;
        }
    }
    
    for(int j =1; j<n; j++){
        if(obstacleGrid[0][j]!=1){
            dp[0][j] = dp[0][j-1];
        }else{
            dp[0][j] = 0;
        }
    }
    
    
    for(int i =1; i<m; i++){
        for(int j=1; j<n; j++){
           if(obstacleGrid[i][j]!=1){ 
               dp[i][j] = dp[i-1][j] + dp[i][j-1];
           }else{
               dp[i][j] = 0;
           }
           //printf("i=%d, j=%d dp[i][j]=%d\n", i, j, dp[i][j]);
        }
    }
    
    return dp[m-1][n-1];
}
