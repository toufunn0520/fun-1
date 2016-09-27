package category.implementation.string.parse;

public class StrToI {

    public static void main(String[] args) {
        System.out.println(new StrToI().myAtoi("9223372036854775987667776555555555555555555555555555555544434556677778888809"));

    }

    public int myAtoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int sign = 1;
        int i = 0;

        while(str.charAt(i) == ' '){
            i++;
        }
        
        if (str.charAt(i) == '+') {
            i++;
        } else if (str.charAt(i) == '-') {
            i++;
            sign = -1;
        }

        double retValue = 0;
        for (; i < str.length(); i++) {
            int digit = str.charAt(i) - '0';
            if (digit > 9 || digit < 0) {
                break;
            }

            retValue = retValue * 10 + digit; 
        }
        
        retValue = sign * retValue;
        
        System.out.println(retValue);
        if(retValue > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        } else if (retValue < Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        } else {
            return (int) retValue;
        }
   }
}
