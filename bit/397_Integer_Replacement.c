int integerReplacement(int n) {
    int c = 0;
    unsigned long long result = n; 
    while (result > 1) {
        if ((result & 1) == 0) {
            result = result >> 1;
        } else if (result == 3 || ((result >> 1) & 1) == 0) {
            --result;
        } else {
            ++result;
        }
        ++c;
    }
    return c;
    
}
