public class Solution {
    public int evalRPN(String[] tokens) {
        int a; 
        int b;
        Stack<Integer> S = new Stack<Integer>();
        for(String s:tokens){
            if(s.equals("+")){
                a = S.pop();
                b = S.pop();
                S.add(a+b);
            }else if(s.equals("-")){
                a = S.pop();
                b = S.pop();
                S.add(b-a);  // key
            }else if(s.equals("*")){
                a = S.pop();
                b = S.pop();
                S.add(a*b);
                
            }else if(s.equals("/")){
                a = S.pop();
                b = S.pop();
                S.add(b/a);  //key
            }else{
                S.add(Integer.parseInt(s));// value only
            }
                
        }
        return S.pop();
        
    }
}
