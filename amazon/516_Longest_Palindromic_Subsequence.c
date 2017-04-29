int max (int p, int q){
    return p>q?p:q;
}
int longestPalindromeSubseq(char* s) {
    if (!s) return 0;
    int len = strlen(s);
    int** res = (int**) malloc(sizeof(int*)*len);
    for (int i = 0; i<len; i++) {
        res[i] = (int*) malloc (sizeof(int)*len);
        memset(res[i], 0,sizeof(int)*len);
    }
    
    for (int i = len-1; i>=0; i--){
        for(int j = i; j<len; j++){
            if(i == j) {
                res[i][i] = 1;
            } else {
                int tmp = 0;
                if(s[i] == s[j]){
                    tmp = res[i+1][j-1] +2;
                }            
                res[i][j] = max(max(res[i+1][j], res[i][j-1]), tmp);
            }
        }
    }
    return res[0][len-1];
}
