
char* addStrings(char* num1, char* num2) {
    if(!num1) return num2;
    if(!num2) return num1;
    int len1 = strlen(num1);
    int len2 = strlen(num2);
    
    int sum = 0;
    int carry = 0;
    int i1 = len1-1;
    int i2 =len2-1 ;
    
    char *res = (char*)malloc(sizeof(char)*10201);
    res[10200] = '\0';
    int i =10199;
    int n1 = 0;
    int n2 = 0;
    
    while(i2>=0 ||  i1>=0){
        if (i1>=0 ) {n1 = num1[i1] -'0';}else{n1 = 0;}
        if (i2>=0 ) {n2 = num2[i2] -'0';}else{n2 = 0;}
        sum = (carry + n1+n2)%10;
        carry =(carry + n1+n2)/10;
        res[i] = '0' + sum;
        i--;
        i2--;
        i1--;
     }
     if(carry) {
         res[i] = '0'+carry;
         i--;
     }
     i++;
     return &(res[i]);
}
