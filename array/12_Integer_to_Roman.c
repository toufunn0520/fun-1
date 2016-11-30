
/*

I: 1
V: 5
X: 10
L: 50
C: 100
D: 500
M: 1000

字母可以重复，但不超过三次，当需要超过三次时，用与下一位的组合表示：
I: 1, II: 2, III: 3, IV: 4
C: 100, CC: 200, CCC: 300, CD: 400

s = 3978
3978/1000 = 3: MMM
978>(1000-100), 998/900 = 1: CM
78<(100-10), 78/50 = 1 :L
28<(50-10), 28/10 = XX
8<(100-1), 8/5 = 1: V
3<(5-1), 3/1 = 3: III
ret = MMMCMLXXVIII
*/
char* intToRoman(int num) {
        char  dict[13][3] = {{'M'},{"CM"},{"D"},{"CD"},{"C"},{"XC"},{"L"},{"XL"},{"X"},{"IX"},{"V"},{"IV"},{'I'}};
        int val[13] = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        char *res = (char*)malloc(sizeof(char)*100);
        int pos =0;
        
        for(int i =0; i<13; i++){
            if(num>=val[i]){
                int count = num/val[i];
                num = num%val[i];
                for(int j =0; j<count; j++){
                    memcpy(&(res[pos]), dict[i], strlen(dict[i]));
                    pos += strlen(dict[i]);
                }
            }
        }
        res[pos]='\0';
        return res;
}
