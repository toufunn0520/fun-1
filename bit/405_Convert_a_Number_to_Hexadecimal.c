
char map[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
/*num are positive*/
void internal(int num, char *res, int* index){
    if(!res) return;
    int cur = 0;
    while( num && (*index)){
            cur = num & 15;
            res[(*index)-1] = map[cur];
            num = num>>4;
            (*index)--;
    }
    return;
}

char* toHex(int num) {
    char *res = malloc(sizeof(char)* 9);
    memset(res, 0, 9);
    int index = 8;
    if(num == 0) {
        res[7] ='0';
        index = 7;
        goto out;
    }
    internal(num, res, &index);
    //printf("%d res=%c len%d\n", index, res[index], strlen(&(res[index])));
out:
    return &(res[index]);
}
