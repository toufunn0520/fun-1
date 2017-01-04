char* convertToTitle(int n) {
    if(n<0) return NULL;
    char* tmp = (char *)malloc(sizeof(char)*100);
    int i = 0;
    while(n){
       tmp[i] =(char)((n-1)%26+65); // key
       n = (n-1)/26; // ！！！key
       i++;
    }
    char* res = (char *)malloc(sizeof(char)* i);
    int k =0;
    for (int j =i-1;j>=0; j--){
        res[k++] = tmp[j];
    }
    res[k]= '\0';
    return res;
}
