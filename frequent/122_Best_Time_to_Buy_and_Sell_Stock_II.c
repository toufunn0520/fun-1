/*Easy just make transcation anytime if it profits*/

int maxProfit(int* prices, int pricesSize) {
    int sum =0;
    if(!prices) return sum;
    for(int i =0; i<pricesSize-1; i++){
            if(prices[i+1]-prices[i]>0){
             sum += prices[i+1]-prices[i];
            }
    }
    return sum;
}
