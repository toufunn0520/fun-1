bool canConstruct(char* ransomNote, char* magazine) {
    if (!ransomNote || !magazine) return false;
    int mlen = strlen(magazine);
    int rlen = strlen(ransomNote);
    if(!rlen) return true;
    int m[26] = {0};
    int n[26] = {0};
   
   for(int i = 0; i<mlen; i++){
       m[magazine[i]-'a'] ++;
   }
   for(int j = 0; j<rlen; j++){
       if(--m[ransomNote[j]-'a'] < 0) return false;
   }
   
    return true;
}
