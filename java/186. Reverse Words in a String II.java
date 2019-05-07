class Solution {
    public void reverseWords(char[] str) {
        // reverse the words and then reverse the whole string
        int size  = str.length;
        int start  = 0;

        for (int i = 0; i <= size ; i++) {
            if (i == size  || str[i] == ' ') {
                inPlaceReverse(start, i-1, str);
                start = i+1;
            } 
        }
        inPlaceReverse(0, size - 1, str);

    }
    
    private void inPlaceReverse(int start, int end, char[] str) {
        while (start < end) {
            char tmp = str[start];
            str[start] = str[end];
            str[end] =  tmp;
            end --;
            start ++;
          }
    }
}
