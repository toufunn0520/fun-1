/*
329. Longest Increasing Path in a Matrix
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/



/*DP the LIP util this point
/*based on other four position as follows
 *
 */
int dx[4] = {1,-1,0,0};
int dy[4] = {0,0,1,-1};
int Row =0;
int Col =0;
int **dp = NULL;
int **flag = NULL;
int MAX(int p, int q){
    return p>q?p:q;
}


int search(int i, int j, int** matrix){
    if(flag[i][j] == 1 ) return dp[i][j];
    //update & initialized dp 
    dp[i][j] =1;
    int nx, ny=0;
    for(int index=0; index<4; index++){
        nx=dx[index]+i;
        ny=dy[index]+j;
        if(nx<Row && nx>=0 && ny<Col && ny>=0){
            if(matrix[i][j]>matrix[nx][ny]) {
                dp[i][j] = MAX(dp[i][j], search(nx, ny, matrix)+1);
            }
        }
    }
    flag[i][j] = 1;
    return dp[i][j];
}


int longestIncreasingPath(int** matrix, int matrixRowSize, int matrixColSize) {
    int len = 0;
    Row = matrixRowSize;
    Col = matrixColSize;
    dp = (int**)malloc(matrixRowSize*sizeof(int*));
    flag = (int**)malloc(matrixRowSize*sizeof(int*));
    
    for (int i=0; i<matrixRowSize; i++){
        dp[i] = (int*)malloc(matrixColSize*sizeof(int));
        flag[i] = (int*)malloc(matrixColSize*sizeof(int));
    }
     
    for(int i =0; i<matrixRowSize; i++){
      for(int j =0; j<matrixColSize; j++){
            dp[i][j] = 0;
            flag[i][j] = 0;
      }
    }
    for(int i =0; i<matrixRowSize; i++){
      for(int j =0; j<matrixColSize; j++){
          dp[i][j] = search(i, j, matrix);
          len = MAX(len,dp[i][j]);
    }
        
    }
    return len;
}
