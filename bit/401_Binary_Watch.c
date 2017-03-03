/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
int countbit(int num){
    int count = 0;
    while(num){
        if(num&1) count++;
        num = num >>1;
    }
    return count;
}

char** readBinaryWatch(int num, int* returnSize) {
     *returnSize = 0;
     int **res = malloc(sizeof(int*)*500); // 11!*n!/(11-n)!
     for (int h=0; h<12; h++){
        for (int m=0; m<60; m++){
            if(countbit(h*64+m) == num) {
                res[*returnSize]= malloc(5);
                memset(res[*returnSize], 0, 5);
                sprintf(res[*returnSize], "%d:%02d",h,m);
              //  printf("Bingo %s\n",res[*returnSize] );
                (*returnSize)++;
            }
        }
     }
    return res;
}
