int mySqrt(int x) {
    int start = 0;
    int end = x;
    int mid = 0;
    if(x <=1) return x;
    
    while(start <= end){
        mid = start + (end-start)/2;
        if(mid < x/mid) {
            start = mid+1;
          //  printf("1 Update result is %d start=%d, end =%d\n", mid, start, end);

        }else if (mid > x/mid) {
            end = mid-1;
         //   printf("2 Update  result is %d  start= %d, end=%d\n", mid, start, end);

        }else{
         //   printf("Bingo result is %d\n", mid);
            end = mid;
            break;
        }
    }
    
    //printf("The final result is %d\n", end);
    return end;
    
}

bool isPerfectSquare(int num) {
    int i = mySqrt(num);
    return (i*i==num);
}
