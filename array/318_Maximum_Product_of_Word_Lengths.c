/*Find a way to find duplicate 26bits*/
int MAX(int p, int q){
    return p>q? p:q;
}

int maxProduct(char** words, int wordsSize) {
    if(!words || !wordsSize ) return 0;
    int result =0;
    int *bits =(int*)malloc(sizeof(int) * wordsSize);
    
    for(int i =0; i<wordsSize; i++){
        bits[i] = 0;
        for(int ii = 0; ii < strlen(words[i]); ii++){
            int shift = words[i][ii] -'a';
            bits[i] |= 1<< shift;
        }
       //printf("bits[%d] =%d\n", i, bits[i]);
    }
    
    //update result
    
    for(int i =0; i< wordsSize; i++){
        for(int j =i+1; j< wordsSize; j++){
            if(!(bits[i] & bits[j])) result = MAX(result, (strlen(words[i])* strlen(words[j])));
        }
    }
    return result;
    
}
