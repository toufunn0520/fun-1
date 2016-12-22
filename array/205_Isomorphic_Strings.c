bool isIsomorphic(char* s, char* t) {
        if (strlen(s) != strlen(t)) return false;
        int m[512] ={0};
        for (int i = 0; i < strlen(t); i++) {
            if (m[(int)s[i]] != m[(int)t[i]+256]) return false;
            m[(int)s[i]] = m[(int)t[i]+256] = i+1;
        }
        return true;
}
