bool isMatch(char* s, char* p) {
    //if (p[0]=='\0') return (s[0]=='\0');
    if (!*p) return (!*s);
    if ( p[1] == '*') {
        return (isMatch (s,p+2) || ( (*s) && (s[0] == p[0] || p[0] =='.') && isMatch(s+1,p)));
    } else {
        return  ((*s) && ((s[0] == p[0] || p[0] =='.') && isMatch(s+1, p+1)));
    }
}
