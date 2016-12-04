bool isStrobogrammatic(char* num) {
    if(!num) return false;
    int len = strlen(num);
    if(len == 0 ) return true;
    for(int i =0 ; i<(len+1)/2; i++){
        if(num[i] != '1' && num[i]!= '8' && num[i]!='6' && num[i]!='9' && num[i]!='0') {
            return false;
        }else{
            if(num[i] == '8' && num[len -1 -i] =='8') continue;
            if(num[i] == '1' && num[len -1 -i] =='1') continue;
            if(num[i] == '0' && num[len -1 -i] =='0') continue;
            if(num[i]=='9' && num[len -1 -i] =='6') continue;
            if(num[i]=='6' && num[len -1 -i] =='9') continue;
            return false;
        }
    }
    
    return true;
}
