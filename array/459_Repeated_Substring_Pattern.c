/*最直接的方法就是找出所有的子串，然后判断这个子串是否可以拼接处最终的字符串.*/
bool repeatedSubstringPattern(char* str) {
    if(!str) return false;
    int len = strlen(str);
    for(int i =0; i<len/2; i++){
        if(len%(i+1)) continue;
        // Enumerate all possible substring
        char *target =(char*)malloc(sizeof(char)*(i+2));
        strncpy(target, str, i+1);
        target[i+1] ='\0';
        int j = i+1;
        for(; j<len; j=j+i+1){
            if(!strncmp(target, &str[j],i+1)){
                continue;
            }else{
                break;
            }
        }
        free(target);
        target= NULL;
        if(j == len) return true;
    }
    return false;
}
