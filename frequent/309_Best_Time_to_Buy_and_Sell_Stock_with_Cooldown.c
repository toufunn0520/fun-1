/*
buy[i]  = max(sell[i-2] - price, buy[i-1]) 
sell[i] = max(buy[i-1] + price, sell[i-1])
由于i只依赖于i-1和i-2，所以我们可以在O(1)的空间复杂度完成算法
 int buy = INT_MIN, pre_buy = 0, sell = 0, pre_sell = 0;
 
buy[i]  = max(rest[i-1] - price, buy[i-1]) 
sell[i] = max(buy[i-1] + price, sell[i-1])
rest[i] = sell[i-1]


*/

int MAX(int p, int q){
    return p>q? p:q;    
}

int maxProfit(int* prices, int pricesSize) {
     int buy = INT_MIN, pre_buy = 0, sell = 0, pre_sell = 0;

     for(int i =0; i<pricesSize; i++){
        pre_buy = buy;
        buy = MAX(pre_sell-prices[i], pre_buy); 
        pre_sell = sell;
        sell = MAX(pre_buy+prices[i], pre_sell);
     }
     
    return sell;
}
