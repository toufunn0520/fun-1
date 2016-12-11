/*Count the frequency of each character. 
 Each character occurs even number of times, then it must be a palindrome. 
 Only one character could appear as even times?*/
bool canPermutePalindrome(char* s) {
    if(!s) return false;
    // Generate a hashmap for the existence 
    int hash[128] = {0};
    int len = strlen(s);
    for(int i =0; i<len; i++){
        hash[s[i]-'0'] ++;
    }
    int allowed =0;
    for(int i =0; i<128; i++){
        //printf("%dth is %d", i, hash[i]);
        if(hash[i]%2) {
            allowed ++;
            if(allowed>1) {
                return false;
            }else{
                continue;
            }
        }
    }
    return true;
    
}
