int MAX (int p, int q){
    return p>q?p:q;
}
int MIN(int p, int q){
    return p<q? p:q;
}
int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    // the main idea is sum of two big rectangle - overlapped arear. But overlapped area could be 0
    int left = MAX(A,E); 
    int right= MAX(MIN(C,G),left);  // smart
    int bottom = MAX(B,F);
    int top = MAX(MIN(D,H), bottom);    // smart
    return ((C-A)*(D-B) + (G-E)*(H-F)-(right-left)*(top-bottom));
}
