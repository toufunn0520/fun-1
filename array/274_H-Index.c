/*Generate a n+1 array put the count into that array space complexity O(n)*/

int hIndex(int* citations, int citationsSize) {
    if(!citations || !citationsSize ) return 0;
    
    int *res = (int *) malloc(sizeof(int)*(citationsSize+1));
    for(int i = 0;i<citationsSize+1; i++) res[i] =0;
    
    // Generate the count
    for(int j=0; j< citationsSize; j++){
        if(citations[j] >=citationsSize) {
            res[citationsSize] ++;
        }else{
            res[citations[j]] ++;
        }
    }
    //Important
    for(int i =citationsSize; i>0;i--){
        /*The 1st elements statisifiec res[i] >= i 
        res[i] the number which are bigger and equal to i 
        */
        if(res[i] >= i) return i;
        res[i-1] += res[i];
    }
    
    return 0; 
}
