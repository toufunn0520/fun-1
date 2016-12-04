/*
differ_count: represents the current post with different color with its previous post(the painting ways)
same_count: represents the current post share the same color with its previous post(the painiting ways)

We could have following trasitinao function
differ_count(i) = differ_count(i-1) * (k-1) + same_count(i-1) * (k-1)
same_count(i) = differ_count(i-1) //cause the current post must have the same color with post i-1, thus we could only use the way that differ_count(i-1)
*/

int numWays(int n, int k) {
    if(!k || !n) return 0;
    int result = 0;
    if(n == 1) return k;
    // when n >=1
    int d = k*(k-1);
    int s = k;
    
    for(int i =3; i<=n; i++) {
        int tmps = s;
        int tmpd = d;
        d = tmpd*(k-1) + tmps*(k-1);
        s = tmpd;
    }
    
    result = d+s;
    return result;
}
