void *helper(char* digits, char** map,  int** res, int* returnSize, char * tmp, int* curlen){
     int total = strlen(digits);
     if (*curlen == total) {
     /* printf("The %dth result: \n",*returnSize);
        for(int i =0; i<total; i++){
            printf("%c\t", tmp[i]);
        }
        printf("\n");
      */
 
        res[*returnSize] = (char *)malloc(sizeof(char) * (total+1));
        memcpy(res[(*returnSize)], tmp, total+1);
        (res[*returnSize])[total]='\0';
        (*returnSize) ++;
        return;
    }
    
    int pos = digits[*curlen] - '0';
    /*For this specific pos, we could have the following number of choices*/
    for(int j = 0; j<strlen(map[pos]);j++){
        tmp[*curlen] = map[pos][j];
        (*curlen) ++;
        helper(digits, map, res, returnSize, tmp, curlen);
        (*curlen) --;
    }
}

/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
char** letterCombinations(char* digits, int* returnSize) {
    
    *returnSize = 0;
    if(!digits||!strlen(digits)) return NULL;
    
    /*Generate mapping from number to characters*/
    char **map = (char**)malloc(sizeof(char*) * 12);

    int start =0; 
    for(int i =2; i<=6; i++){
        map[i] = (char*)malloc(sizeof(char) * 4);
        map[i][3] = '\0';
        for(int j =0; j<3; j++){
            map[i][j]='a'+ start;
         //   printf("i=%dth j=%dth map[i][j]=%c\n", i, j, map[i][j]);
            start++ ;
        }
    }
    
    map[0] =(char*) malloc(sizeof(char)*1);
    map[0][1] = '\0';
    map[1] =(char*) malloc(sizeof(char)*1);
    map[1][1] = '\0';
    map[7] =(char*) malloc(sizeof(char)*5);
    map[7][0] = 'p';
    map[7][1] = 'q';
    map[7][2] = 'r';
    map[7][3] = 's';
    map[7][4] = '\0';
    map[8] =(char*) malloc(sizeof(char)*4);
    map[8][0] = 't';
    map[8][1] = 'u';
    map[8][2] = 'v';
    map[8][3] = '\0';
    map[9] =(char*) malloc(sizeof(char)*5);
    map[9][0] = 'w';
    map[9][1] = 'x';
    map[9][2] = 'y';
    map[9][3] = 'z';
    map[9][4] = '\0';


    int len = strlen(digits);
    int totalen = 1;
    
    for(int i =0; i<len; i++){
        int index = digits[i] -'0';
        totalen = totalen * strlen(map[index]);
    }
    
   //printf("Estimated len is %d strlen =%d\n", totalen, len);
    
    /*pre-alloc final result*/
    char** res = (char**) malloc(sizeof(char*) * totalen);
    int curlen = 0;
    char *tmp = (char**) malloc(sizeof(char*) * len+1);
     for(int i =0; i<len; i++){
      tmp[i] ='\0';
    }
    tmp[len] = '\0';
    
    helper(digits, map, res, returnSize, tmp, &curlen);
    
    return res;

}
