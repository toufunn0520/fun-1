/*int compare(const void* p , const void* q){

    int l = *(int*)p;
    int r = *(int*)q;
    return l-r;
}
void* genString(char* s, int num, int offset, char* res){
    if(!num) return ;
    for(int i = 0; i<num; i++){
        res[offset+i] = s;
    }
    return ;
}*/
char* frequencySort(char* s) {
    if(!s) return s;
    int len = strlen(s);
    char* res = malloc(sizeof(char)* (len+1));
    memset(res, 0, sizeof(char)* (len+1));
    
    int count[128] = {0};
    char **bucket= malloc(sizeof(char*)*(len+1));
    
    for(int i = 0; i<len; i++){
        bucket[i] = malloc(sizeof(char)*(len+1));
        memset(bucket[i], 0, len+1);
        count[(int)(s[i])]++;
    }
    
    bucket[len] = malloc(sizeof(char)*(len+1));
    memset(bucket[len], 0, len+1);
    
    for (int i = 0; i<128; i++) {
        int index = count[i];
        if(!index) continue;
        int curlen = strlen(bucket[index]);
        char cur = i;
        for(int j =0; j<index; j++){
            bucket[index][j+curlen] = cur;
        }
    }
    
    char *final= res;
    for(int i = len; i> 0; i--){
        int curlen = strlen(bucket[i]);
        if (curlen) {
            memcpy(res, bucket[i], curlen);
            res += curlen;
        }
        free(bucket[i]);
    }
    free(bucket);
    return final;
}
