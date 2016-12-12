/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */

char** generatePossibleNextMoves(char* s, int* returnSize) {
    *returnSize =0;
    if(!s|| strlen(s)<=1) return NULL;
    
    int len = strlen(s);
    char** res = (char**)malloc(sizeof(char*)*100*len);

    for(int i=0; i<strlen(s)-1; i++){
        if(s[i]==s[i+1] && s[i] =='+'){
            s[i]= '-';
            s[i+1]= '-';
            
            res[(*returnSize)] = (char*)malloc(sizeof(char)*(strlen(s)+1));
            strncpy(res[(*returnSize)], s, strlen(s));
            res[(*returnSize)][strlen(s)] = '\0';
            
            s[i]= '+';
            s[i+1]= '+';
            (*returnSize)++;
        }
    }
    return res;
}
