int romanToInt(char* s) {
        if(!s) return 0;
        int size = strlen(s);
        char  dict[13][3] = {{'M'},{"CM"},{"D"},{"CD"},{"C"},{"XC"},{"L"},{"XL"},{"X"},{"IX"},{"V"},{"IV"},{'I'}};
        int val[13] = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        int res =  0;
        int index =0;
        int pos =0;
        while(index<13 && pos<size){
            //printf("Try to Match %s\n", dict[index]);
            if(!strncmp(&(s[pos]),dict[index],strlen(dict[index]))){
              //  printf("Bingo! to Match %s\n", dict[index]);
                res += val[index];
                pos += strlen(dict[index]);
            }else{
                index++;
            }
        }
        return res;
}
