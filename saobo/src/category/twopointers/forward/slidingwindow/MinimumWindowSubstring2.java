package category.twopointers.forward.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring2 {

    public static void main(String[] args){
        String s =  "dbcadbacbcede";
        String t =  "abcde";
        System.out.println(minSequtentialWindow(s,t));
    }
    
    public static String minSequtentialWindow(String S, String T) {
        if (S == null || T == null || S.length() == 0 || T.length() == 0) return "";
        
        // Index for characters in T
        Map<Character, Integer> index = new HashMap<>();
        // The most recent apperance of T[i] in S
        int[] latest = new int[T.length()];
        // The minimum length of the substring in S that covers T[0..i]
        int[] minLen = new int[T.length()];
        
        Arrays.fill(latest, -1);
        Arrays.fill(minLen, Integer.MAX_VALUE);
        for (int i = 0; i < T.length(); i++) {
            index.put(T.charAt(i), i);
        }
        
        String res = "";
        int resLen = Integer.MAX_VALUE;
        for (int i = 0; i < S.length(); i++) {
            
            if(minLen[0] != 1 && S.charAt(i) != T.charAt(0))
                continue;
            
            // If it's a character in T
            if (index.containsKey(S.charAt(i))) {
                // Find the index in T
                int idx = index.get(S.charAt(i));
                
                if (idx == 0) {
                    // If it's the first element in T, then min len is 1
                    minLen[idx] = 1;
                } else if (minLen[idx - 1] != Integer.MAX_VALUE) {
                    // If T[idx - 1] exists in the S[0..i]
                    // then the min len is the number of elements from i to last appreance of T[idx-1] in S
                    // plus the min len of the previous element
                    minLen[idx] = i - latest[idx - 1] + minLen[idx - 1];
                }
                
                latest[idx] = i;
                
                if (idx == T.length() - 1 && minLen[idx] < resLen) {
                    resLen = minLen[idx];
                    res = S.substring(i - resLen + 1, i + 1);
                }
            }
        }
        
        return res;
    }
}
