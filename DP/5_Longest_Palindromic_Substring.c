
/*
P[i,j] = 1  if i ==j
        =  S[i] ==S[j]   if j = i+1
        =  S[i] == S[j] && P[i+1][j-1]  if j>i+1
*/

char* longestPalindrome(char* s) {
    if(!s) return s;
    
    int len = strlen(s);
    bool** dp = (bool**)malloc(sizeof(bool*) * len);
    for(int i =0; i<len; i++){
        dp[i]= (bool *)malloc(sizeof(bool) * (len));
        for(int j =0; j<len; j++){
            dp[i][j] = false;
        }
        dp[i][i] = true;
    }
    
    int start = 0; int end =0; int max = 1; // max = end -start+1
    for(int i = 0; i<len; i++){
        for(int j =0; j<i; j++){
            if(i==j+1) {
                dp[j][i] = (s[i]==s[j])?true:false;
            }else{
                //i>j+1
                dp[j][i] = (s[i] == s[j])? (dp[j+1][i-1]):false;
              //  printf("Check j=%d i=%d haha=%d huhu=%d\n", j, i, dp[j+1][i-1], dp[j][i]);
            }
            //update max

            if((dp[j][i]) && ((i-j+1)>max)){
                start = j;
                end = i;
                max = i-j+1;
              //  printf("About to update start=%d end =%d, max=%d\n",start, end, max);
            }
        }

    }
    char* res = (char*)malloc(sizeof(char)*(max+1));
    strncpy(res, &s[start], max);
    res[max] ='\0';
    for(int i =0; i<len; i++){
        free(dp[i]);
        dp[i]=NULL;
    }
    return res;
}
