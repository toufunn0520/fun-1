/*Rebuild count the odd number of Chaters + 1even number of Charters*/

int convert(char s){ 
    int num = 0;
    if (s <= 'Z' && s >='A'){
        num = (s - 'A') + 26 ;
    }else{ 
        num = s -'a';
    }
   // printf(" Conver char=%c into val=%d\n", s, num);
    return num;
}

int longestPalindrome(char* s) {
 if(!s) return 0;
 int len = strlen(s);
 int result = 0;
 int flag =0;
 
 /*Generete the key-> value (charater->counter)*/
 /*Max is 48*/
 int data[52] = {0};
 for (int j =0; j< 49; j++){
      data[j] = 0;
 }

 for(int i =0; i<len; i++){
     data[convert(s[i])] ++;
 }
 /*The final result will take the even occurace*/
 for (int j =0; j< 52; j++){ 
  //  printf("i=%d data[i]=%d\n", j, data[j]);
    result += ((data[j]/2)*2);
    if(data[j]%2 && !flag) flag=1;
 }
 result += flag;
 //printf("Result = %d flag=%d\n", result, flag);
 return result;
}
