char* addBinary(char* a, char* b) {
    if(!a) return b;
    if(!b) return a;
    int lena = strlen(a);
    int lenb = strlen(b);
    int len = lena>lenb? lena:lenb;
    
    char* result=(char*)malloc((len+2)*sizeof(char));
    result[len+1] = '\0';
    
    int sum =0, carries =0;
    int i =lena-1;
    int j = lenb-1;
    int ii = len+1;
  //  printf("len=%d lena=%d lenb=%d\n", len+1,lena, lenb);
    
    while( i>=0 && j>=0){
       ii--;
       sum = (int)(a[i] - '0') + (int)(b[j]- '0') + carries;
       carries = sum/2;
       result[ii] = sum%2 +'0';
      // printf("sum=%d carries=%d reslt=%d\n", sum,carries,result[ii]);
       i--;
       j--;
    }
    
    while(i>=0){
       ii--;
       sum = (int)(a[i] - '0')  + carries;
       carries = sum/2;
       result[ii] = sum%2 +'0';
       i--;
    }
    
    while(j>=0){
       ii--;
       sum = (int)(b[j] - '0')  + carries;
       carries = sum/2;
       result[ii] = sum%2 +'0';
       j--;
    }
    
    if(carries==1) {
        ii--;
        result[ii] = '1' ;
    };
    //printf("ii=%d result[ii]=%c, len=%d\n", ii, result[ii], strlen(result+ii));
    return result+ii;

}
