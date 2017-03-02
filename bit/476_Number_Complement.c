int findComplement(int num) {
    int res =0;
    for(int i =0; i<32; i++){
        if(num ==0) break;
        int tmp = num & 1;
        res |= ((tmp^1)&1)<<i;
        //printf("the %d is %d\n", i, ((tmp^1)&1));
        num = num>>1;
    }
    return res;
}
