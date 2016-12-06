/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
 
 int MAX(int p, int q){
     return p>q?p:q;
 }
  int MIN(int p, int q){
     return p<q?p:q;
 }
 
char* generateRange(int start, int end){
   char s[100];
   char e[100];
   
   sprintf(s,"%d",start);
   sprintf(e,"%d",end);
   char* cur = (char*) malloc(sizeof(char)*202);
   printf("Comming Range is %d to %d\n", start, end);

   strncpy(cur,s, strlen(s));
   if(start!=end) {
       cur[strlen(s)] = '-';
       cur[strlen(s)+1] = '>';
       strncpy(&(cur[strlen(s)+2]), e, strlen(e));
   }
   cur[strlen(cur)] ='\0';
   printf("check Range is %s\n", cur);
   return cur;
}
char** findMissingRanges(int* nums, int numsSize, int lower, int upper, int* returnSize) {
    *returnSize = 0;
    if(!nums && !numsSize || upper<lower) return NULL;
    char** res = (char**) malloc(sizeof(char*) * (100));
    long long prev = lower - 1;  
    for (int i=0; i<=numsSize; ++i) {  
        long long cur = (i==numsSize)?(upper+1):nums[i];    //key
       // printf("cur=%lld prev=%lld\n", cur, prev);
        if ( cur-prev >= 2 ) {   //key
            res[(*returnSize)] = generateRange(prev+1, cur-1);  
            (*returnSize)++;
        }  
        prev = cur;  
    }  
    return res;
    
}
