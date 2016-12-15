int ABSMIN(int p, int q){
    if(p<0) p =-p;
    if(q<0) q =-q;
    return p<q?p:q;
}
/*Create a special case for when word1 == word2*/
int shortestWordDistance(char** words, int wordsSize, char* word1, char* word2) {
    if(!words || !wordsSize || !word1 || !word2) return 0;
    
    bool same = false;
    if(!strcmp(word1, word2)) same = true;
    int distance = 20000; 
    int l1 = -1;
    int l2 = -1;
    for(int i = 0;  i< wordsSize; i++){
        if(!strcmp(words[i], word1)|| !strcmp(words[i], word2) ){
            if(same){
                if(l2!=-1){
                    l1 = l2;
                    l2 = i;
                    distance = ABSMIN(distance, l2-l1);
                }else {
                    l2 = i;
                }
            }else{
                 if(!strcmp(words[i], word1)){
                     l1 = i;
                     distance=(l2==-1)?distance:ABSMIN(distance, l2-l1);
                 }else{// must be !strcmp(i, words2)
                     l2 = i;
                     distance=(l1==-1)?distance:ABSMIN(distance, l2-l1);
                 }
                 
            }
        }
        
    }
    return distance;
    
}
