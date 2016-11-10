#define MOD 1337

/*223 = (22)10 * 23, 所以我们可以从b的最高位开始，算出个结果存入res，然后到下一位是，res的十次方再乘以a的该位次方再对1337取余*/
/*c mod m = [(a mod m) ⋅ (b mod m)] mod m*/

int powmod(int a, int x){
    if(x == 0) return 1;
    if(x == 1) return a%MOD;
    return powmod(a % 1337, x / 2) * powmod(a % 1337, x - x / 2) % 1337;
}

int superPow(int a, int* b, int bSize) {
    //convert b into a number
    int res = 1;
    for(int i =0; i<bSize; i++){
        res = powmod(res, 10) * powmod(a, b[i])% MOD;
    }
    return res;
    
}
