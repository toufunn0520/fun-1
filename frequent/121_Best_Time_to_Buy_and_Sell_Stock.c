/* */
int maxProfit(int* prices, int pricesSize) {
    if(!prices) return 0;
    int maxProfit = 0;
    int curMin = prices[0];
        
    for (int i = 1; i < pricesSize; i++) {
      curMin = curMin>prices[i]? prices[i]: curMin;
      maxProfit = (maxProfit< prices[i] - curMin) ? (prices[i] - curMin):maxProfit;
    }
    return maxProfit;
}
