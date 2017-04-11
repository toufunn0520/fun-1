int* grayCode(int n, int* returnSize) {
    int range = 1<<n;
    int *res = malloc(sizeof(int)*range);
    for(int i =0; i<range;i++){
        res[i] = i^(i>>1);
    }
    *returnSize = range;
    return res;
}
