/*DP in O(nk) costsRowSize = n, costsColSize =k */
/*假如这个颜色与之前i-1号房子的颜色相同，我们选择min2，否则选择min1
比较完所有颜色以后我们记录下来当前的curMin1，curMin2以及current color， 
更新min1，min2和lastColor，就可以继续计算下一个房子了。*/

int minCostII(int** costs, int costsRowSize, int costsColSize) {
    if( !costs || !costsRowSize || !costsColSize) return 0;
    
    int min= 0; int secondmin =0; int last =-1;
    
    for(int i =0; i< costsRowSize; i++){
        int curmin = INT_MAX;
        int cursecondmin = INT_MAX;
        int cur_last = -1;
        
        for(int j =0; j< costsColSize; j++){
            int cur_costs= costs[i][j] + ((j==last)? secondmin: min);
            if(cur_costs < curmin){
                cursecondmin = curmin;
                curmin = cur_costs;
                cur_last = j;
            }else if(cur_costs < cursecondmin){
                cursecondmin = cur_costs;
            }
        }
        
        min = curmin;
        secondmin=cursecondmin;
        last = cur_last;
    }
    
    
    return min;
}
