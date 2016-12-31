void reverse(char* s, int start, int end){
    if(!s) return s;
    while(start < end){
        char tmp = s[start];
        s[start] = s[end];
        s[end] = tmp;
        end--;
        start ++;
    }
}
void reverseWords(char *s) {
    if(!s) return s;
    // revers the whole sentense
    int len = strlen(s);
    reverse(s, 0, len-1);
    int index =0 ;// always slower than i/j
    for(int i = 0; i<len;i++){
        if(s[i]!= ' '){ 
            if(index!=0){
                s[index++] = ' ';
            }
            int j =i;
            while(j<len && s[j]!=' '){ s[index++] = s[j++];}
            reverse(s, index-(j-i), index-1);
            i=j;
        }
    }
    //clear all remaining stuff 
    memset(&s[index], 0, len-index);
    return s;
}
