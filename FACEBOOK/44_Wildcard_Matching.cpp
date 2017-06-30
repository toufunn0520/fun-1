/*
 * Kind of greedy but very smart. Record laststart position for both P and S. 
 * P needs to be empty once exit.
*/

bool isMatch(char* s, char* p) {
    
    char* laststar = NULL;
    char* starmatch = s;
    
    while (*s) {
        if ( (*s)==(*p) || (*p)=='?') { 
            s++;
            p++;
            continue;
        } else if (*p == '*') {
            laststar = p++;
            starmatch = s;
        } else if (laststar) {
            p = laststar+1;
            s = ++starmatch;
            continue;
        } else {
            return false;
        }
    }
    
    while ((*p)=='*') {
        p++;
    }
    
    return !(*p);
}
