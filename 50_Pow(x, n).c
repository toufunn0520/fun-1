/*pow(x, n) = pow(x,n/2) * pow(x, n/2)* (pow n%2)
 *Time effeciency is not to calculate it again for myPowhelper(x,n/2)
 *
 */


double myPowhelper(double x, int n){
     if(n ==0) return 1;
     if(n ==1) return x;
     if(n ==2) return x*x;
     double result = myPowhelper(x, n/2);
     double final = result *result;
     if(n%2) final = final *x;
     return final;
     
}

double myPow(double x, int n) {
    
     if(n<0) return 1/myPowhelper(x, -n);
     return myPowhelper(x, n);
}
