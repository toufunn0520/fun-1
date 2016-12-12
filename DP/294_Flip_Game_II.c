/*BackTrace + DP similar as I*/
bool canWin(char* s) {

    if(!s|| strlen(s)<=1) return false;
    
    int len = strlen(s);
    for(int i=0; i<strlen(s)-1; i++){
        if(s[i]==s[i+1] && s[i] =='+'){
            s[i]= '-';
            s[i+1]= '-';
            char* tmp = (char*)malloc(sizeof(char)*(len+1));
            strncpy(tmp,s,strlen(s));
            tmp[strlen(s)] = '\0';
            if(!canWin(tmp)){
                return true;
            }
            if(tmp){
                free(tmp);
                tmp = NULL;
            }
            s[i]= '+';
            s[i+1]= '+';
        }
    }
    return false;
}
