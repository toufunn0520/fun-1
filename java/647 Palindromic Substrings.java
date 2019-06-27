/*Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
*/


/*Complexity Analysis

Time Complexity: O(N^2)O(N 
2
 ) where NN is the length of S. Each expansion might do O(N)O(N) work.

Space Complexity: O(1)O(1).
*/


class Solution {
    public int countSubstrings(String s) {
        //center is the startpoint
        if (s == null) return 0;
        int length = s.length();
        
        // we could have 2 * N - 1 center point 
        int res = 0;
        for (int i = 0; i < 2 * length - 1; i++) {
            int left = i / 2;
            int right = left + i % 2;
            // keep trying 
            while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
                res ++;
                left --;
                right ++;
            }
        }
        return res;
        
        
    }
}
