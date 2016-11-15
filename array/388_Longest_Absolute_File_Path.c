/*首先将字符串以'\n'进行分割，得到目录/文件的列表，记为parts
然后统计各目录/文件中'\t'的个数，表示当前目录/文件的深度
遍历parts，若栈顶元素的深度不小于parts的深度，则弹出栈顶元素，重复此过程。
然后将新的深度压入栈中，顺便统计当前目录的总长度。*/
/*stack or hashmap [level , strlen]*/
int Calculate_Level(char * input){
        if(!input) return 0;
        int len = strlen(input);
        int i =0;
        while(i <len && input[i]=='\t'){
            i++;
        }
        return i;
}

bool isFile(char * input){
        if(!input) return false;
        int len = strlen(input);
        int i =0;
        while(i <len){
            if(input[i]=='.') return true;
            i++;
        }
        return false;
}
    
int MAX(int p, int q){
        return p>q?p:q;
}

int lengthLongestPath(char* input) {
 /*Find the total number of path*/
  if(!input) return 0;
  int totallen = strlen(input);
  int n = 0;
  int i =0;
  int longest =0;
  while(i<totallen){
      if(input[i] == '\n') n++;
      i++;
  }
  /*Allocate n+1 D array */
  int * res = (int*) malloc (sizeof(int)* (n+1));
  for (int j =0; j<=n; j++){
     res[j] = 0;
  }
  
  /*Generate char array*/
  char ** content =  (char **) malloc(sizeof(char *)*(n+1));
  i =0;
  int index =0;
  int start =0;
  while(i<totallen){
      if(input[i] == '\n'){
          content[index] = (char *)malloc(sizeof(char) * (i-start+1));
          memcpy(content[index], &input[start], i -start);
          content[index][i-start] ='\0';
          index++;
          start = i+1;
      }
      i++;
  }
  content[index] = (char *)malloc(sizeof(char) * (totallen-start+1));
  memcpy(content[index], &input[start], totallen-start);
  content[index][totallen-start] ='\0';


  for (int j = 0; j<= n; j++){
 //     printf("Chekck %d is %s\n", j ,content[j]);
      int cur_len = strlen(content[j]);
      int cur_level = Calculate_Level(content[j]);

      cur_len = cur_len - cur_level;
 //       printf("Chekck %d is %s level=%d strlen=%d\n", j ,content[j], cur_level, cur_len);

      if(isFile(content[j])) {
          longest = MAX(longest, res[cur_level-1]+cur_len);
      }else {
          res[cur_level] = (cur_level>0)? res[cur_level-1]+1+cur_len: cur_len+1;
   //       printf("Update level =%d len=%d\n", cur_level,res[cur_level]);
      }
      
  }
  return longest;
}
