void reverse(char *s, int start, int end){
    while(start<end){
        char tmp = s[start];
        s[start] = s[end];
        s[end] =tmp;
        start ++;
        end--;
    }
    return;
}

void reverseWords(char *s) {
    if(!s || strlen(s)==0) return s;
    int len = strlen(s);
    reverse(s, 0, len-1);
    int start =0;
    int end = start;
    while(end<len){
        if(s[end] == ' '){
            reverse(s, start, end-1);
            start = end+1;
        }
        end ++;
    }
    reverse(s, start, len - 1);//reverse the last word, if there is only one word this will solve the corner case
    return s;
}
