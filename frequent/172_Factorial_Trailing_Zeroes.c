/*Find a pair of 2 and 5  log5n*/
int trailingZeroes(int n) {
    int count = 0;
    while(n/5){
        //所以除了计算n/5， 还要计算n/5/5, n/5/5/5, n/5/5/5/5, ..., n/5/5/5,,,/5直到商为0
        count += n/5;
        n = n/5;
    }
   // printf("result is %d\n", count);
    return count;
}
