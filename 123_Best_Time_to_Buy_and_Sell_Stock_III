/*DP 
* Extension of sel stock I 
left[i] 0->i max profit
right[i] i->n-1 max profit
*/
int MIN(int p, int q){
    return p<q? p:q;
}
int MAX(int p, int q){
    return p>q? p:q;
}
int maxProfit(int* prices, int pricesSize) {
    int profit =0;
    if(!prices || pricesSize<2) return profit;
    
    int * left = (int*) malloc (pricesSize*(sizeof(int)));
    int * right = (int*) malloc (pricesSize*(sizeof(int)));

    int min = prices[0];
    int max = prices[pricesSize -1];
    for(int i =0; i<pricesSize; i++){
        left[i] =0;
        right[i] =0;
    }
    for(int i =1; i<pricesSize; i++){
        min = MIN(min,prices[i]);
        left[i] = MAX(left[i-1], prices[i]-min);
      //  printf("%dth, left=%d\n", i, left[i]);
    }
    for(int i =pricesSize-2; i>=0; i--){
        max = MAX(max,prices[i]);
        right[i] = MAX(right[i+1], max-prices[i]);
              //  printf("%dth, right=%d\n", i, right[i]);

    }
    //
    for(int i =0; i<pricesSize; i++){
        profit=MAX(profit, left[i]+right[i]);
    }
    return profit;
}
