char* convert(char* s, int numRows) {
    if(!s) return NULL;
    int len = strlen(s);
    char** res = (char**)malloc(sizeof(char*)*numRows);
    char* final = (char*)malloc(sizeof(char)*(len+1));
    for(int i = 0; i<numRows; i++){
        res[i] = (char*)malloc(sizeof(char)*(len+1));
        memset(res[i], 0,sizeof(char)*(len+1));
    }
    int index =0;
    
    while(index<len){
        for(int i=0; i<numRows && index<len;i++){    // vertically down
            int cur = strlen(res[i]);
            res[i][cur] = s[index];
            index ++;
        }
        for(int i=numRows-2; i>=1 && index<len; i-- ){  // obliquely up  
            int cur = strlen(res[i]);
            res[i][cur] = s[index];
            index++; 
        }
    }
    
    // concate them together
    int start =0;
    for(int i =0; i<numRows; i++){
        memcpy(&final[start],res[i],strlen(res[i]));
        start += strlen(res[i]);
    }
    //memeory limitation exceed
    for(int i =0; i<numRows; i++){
      free(res[i]);
      res[i] = NULL;
    }
    free(res);
    res = NULL;
    final[len]='\0';
    return final;
}
