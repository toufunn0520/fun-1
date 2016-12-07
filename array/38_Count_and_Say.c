char* countAndSay(int n) {
    if(!n) return NULL;
    char* prev = (char*)malloc(sizeof(char)*10000*n);
    char* current = (char*)malloc(sizeof(char)*10000*n);
    current[0] ='1';
    current[1] ='\0';
    
    for(int i =2; i<=n; i++){
        memset(prev, 0, 10000*n*sizeof(char));
        memcpy(prev, current, strlen(current)*sizeof(char));
        int len = strlen(prev);
        prev[len]='\0';
        memset(current, 0, 10000*n*sizeof(char));
        
        int count = 1;
        char cur = prev[0];
       // printf("Start %d %s\n",i, prev);
        for(int j =0; j<len-1; j++){
            if(prev[j]==prev[j+1]) {
                count++;
                continue;
            }else{
             // printf("About to generate %c : %d at pos%d\n",cur,count,strlen(current));
               sprintf(&(current[strlen(current)]),"%d",count);
               strncpy(&(current[strlen(current)]), &cur, 1);
               count =1;
               cur = prev[j+1];
            }
        }
       // printf("About to generate %c : %d at pos%d\n",cur,count,strlen(current));
        sprintf(&(current[strlen(current)]),"%d",count);
        strncpy(&(current[strlen(current)]), &cur, 1);
        current[strlen(current)] = '\0';  // key forget
    }
    free(prev);
    prev= NULL;
    return current;
}
