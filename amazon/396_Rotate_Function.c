int MAX (int p, int q){
    return p>q? p:q;
}
int maxRotateFunction(int* A, int ASize) {
  if (!A || !ASize) return 0;
  int sum = 0;
  int F = 0; //like F(0)
  int max = INT_MIN;
  for (int i = 0; i<ASize; i++) {
      sum += A[i];
      F += i*A[i];
  }
  max = F; // what if there is one element
  for (int i = ASize-1; i>=1; i--) {
      F = F + sum - (ASize)*A[i];
      max = MAX(F, max);
  }
  return max;
}
