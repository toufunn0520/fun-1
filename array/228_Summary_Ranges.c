/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
char * generateString(int start, int len){
      char *buf = (char *)malloc(sizeof(char)* (13*(len+1)));
      memset(buf, 0, 13*(len+1));
      
      int cur =0;
      sprintf(buf, "%d", start);
      cur=strlen(buf);

      if(len > 0){
        sprintf(&buf[cur], "->%d", start+len);
        cur=strlen(buf);
      }
      
    //  printf("Check %s\n", buf);
      return buf;
}

char** summaryRanges(int* nums, int numsSize, int* returnSize) {
    char** res = (char**) malloc(sizeof(char*) * numsSize);
    int start =0;
    int len = 0;
    int index =0;
    
    for(int i = 1; i<= numsSize; i++){
        while(nums[i]-nums[i-1] ==1 && i<numsSize ) {
            len++;
            i++;
        }
        
        res[index] = generateString(nums[start], len); 
        index ++;
        start = i;
        len =0;
    }
    
    *returnSize =index;
    return res;
}
