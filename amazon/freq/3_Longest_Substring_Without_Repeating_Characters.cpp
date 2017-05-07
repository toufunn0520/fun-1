int lengthOfLongestSubstring(char* s) {
    int *map[256] = {0};
    int len = 0;
    int j =0 ;
    for (int i=0; i<strlen(s); i++){
        while(j<strlen(s) && map[(int)s[j]] ==0){
            map[(int)s[j]] = 1;
            len = len<(j-i+1)?j-i+1:len;
            j++;
        }
        map[(int)s[i]] = 0;
    }
    return len;
}
