/*DP
  Rowsize = costsRowSize;
  last select
  dp[0]
*/

int MIN(int p, int q){
    return p<q?p:q;
}

int minCost(int** costs, int costsRowSize, int costsColSize) {
    
    if(!costs || !costsRowSize || costsColSize != 3) return 0;
    int** dp = (int**) malloc(sizeof(int*)*costsRowSize);
    
    for(int i =0; i<costsRowSize; i++){
        dp[i]= (int*)malloc(sizeof(int)*costsColSize);
         for(int j =0; j<costsColSize; j++){
            if(i==0){
                dp[i][j] = costs[i][j];
            }else{
                dp[i][j] = 0;
            }
         }
    }
    
    for(int i =1; i<costsRowSize; i++){
        dp[i][0] = MIN(dp[i-1][1],dp[i-1][2]) + costs[i][0];
        dp[i][1] = MIN(dp[i-1][0],dp[i-1][2]) + costs[i][1];
        dp[i][2] = MIN(dp[i-1][0],dp[i-1][1]) + costs[i][2];
    }
    
    return MIN(dp[costsRowSize-1][0], MIN(dp[costsRowSize-1][1], dp[costsRowSize-1][2]));
}
