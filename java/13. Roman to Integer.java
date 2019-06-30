class Solution {
    public int romanToInt(String s) {
        int length = s.length();
        int last = 0;
        int res = 0;
        for (int i = 0; i < length; i++) {
            int num = stringToNum(s.charAt(i));
            if (num <= last) {
                res += num;
            } else{
                res -= last*2;
                res += num;
            }
            last = num;
        }
        return res;
    }
    
    private int stringToNum(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M': 
                return 1000;
            default:
                return 0;
        }
    }
}
