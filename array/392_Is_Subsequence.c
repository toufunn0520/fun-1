bool isSubsequence(char* s, char* t) {
     if(!s) return true;
     if(!t) return false;
     
     int lent = strlen(t);
     int lens = strlen(s);
     
     int result = 0;
     int index =0;
     for(int i =0; i<lent; i++){
         if(index < lens){
             if(s[index] == t[i]){
                 index++;
             }
         }
         if(index == lens) break;
     }
     
     return index == lens?true:false;
}
