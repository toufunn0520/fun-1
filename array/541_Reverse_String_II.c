int MIN(int p, int q){
    return p<q?p:q;
}
void* reverse(char* s, int start, int end, int len){
    if(!s) return ;
    if(start >=len) return s;
    //key len-1
    end = MIN(len-1, end);
    while(start<end){
        char tmp = s[start];
        s[start++] = s[end];
        s[end--] = tmp;
    }
    return;
}

char* reverseStr(char* s, int k) {
    if(!s) return s;
    int len = strlen(s);
    int index = 0 ;
    while(index<len){
        reverse(s,index,index+k-1, len);
        index += 2*k;
    }
    return s;
}
