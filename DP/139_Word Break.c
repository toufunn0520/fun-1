/*　一个字符串S，它的长度为N，如果S能够被“字典集合”（dict）中的单词拼接而成，那么所要满足的条件为： 
* F(0, N) = F(0, i) && F(i, j) && F(j, N); 
* 这样子，如果我们想知道某个子串是否可由Dict中的几个单词拼接而成就可以用这样的方式得到结果（满足条件为True, 不满足条件为False）存入到一个boolean数组的对应位置上，这样子，最后boolean 数组的最后一位就是F(0, N)的值，为True表示这个字符串S可由Dict中的单词拼接，否则不行！ */


bool isExited(char *s, int start, int end,  char** wordDict,int wordDictSize ){
    int len = 0 ;
   // if(end>0)  printf("Here start=%d end=%d s[start]=%c, s[end]=%c\n",start, end, s[start], s[end-1]);

    for(int i =0; i<wordDictSize; i++ ){
        if(strlen(wordDict[i]) != end-start) continue;
        len = 0; 
        int tmp = start;
        /*tmp = end-1!!!*/
       // printf("Protential %s\n", wordDict[i]);
        while(wordDict[i][len] == s[tmp] && tmp <=end-1){
            len++;
            tmp++;
        }
        if (len == end-start) return true;
    }
    return false;
}


bool wordBreak(char* s, char** wordDict, int wordDictSize) {
    /*DP methods*/
    if(!s || !wordDict || !wordDictSize) return false;
    int len = strlen(s);
    bool *match = (bool *)malloc(sizeof(bool) * (len+1));
    for(int i =0; i<len+1; i++){
        match[i] = false;        
    }
    match[0] = true;
    for(int i =0; i< len+1; i++){
    //    printf("Checking %dth\n", i);
        for(int j =0; j<i; j++){
            if(match[j] && isExited(s,j,i,wordDict,wordDictSize)){
             match[i] = true;
        //     printf("The %dth is true\n", i);
             break;   
            }
        }
    }
    return match[len];
}
