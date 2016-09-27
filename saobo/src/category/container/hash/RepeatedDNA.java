package category.container.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepeatedDNA {

    /**
     * [Leetcode 187] https://leetcode.com/problems/repeated-dna-sequences/
     * 
     * <pre>
     * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA,
     * it is sometimes useful to identify repeated sequences within the DNA.
     * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
     * 
     * For example,
     * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
     * 
     * Return:
     * ["AAAAACCCCC", "CCCCCAAAAA"].
     * </pre>
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<String>();
        if (s.length() < 10)
            return result;

        HashMap<Long, Integer> hashMap = new HashMap<Long, Integer>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String subStr = s.substring(i, i + 10);
            Long num = string2Num(subStr);
            if (hashMap.containsKey(num)) {
                hashMap.put(num, hashMap.get(num) + 1);
            } else
                hashMap.put(num, 1);
        }

        for (Map.Entry<Long, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(num2String(entry.getKey()));
            }
        }

        return result;
    }

    private String num2String(long num) {
        StringBuilder str = new StringBuilder();
        while (num > 0) {
            if (num % 10 == 1)
                str.append('A');
            else if (num % 10 == 2)
                str.append('C');
            else if (num % 10 == 3)
                str.append('G');
            else if (num % 10 == 4)
                str.append('T');
            num = num / 10;
        }
        return str.reverse().toString();
    }

    private long string2Num(String s) {
        long ret = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                ret = ret * 10 + 1;
            } else if (s.charAt(i) == 'C') {
                ret = ret * 10 + 2;
            } else if (s.charAt(i) == 'G') {
                ret = ret * 10 + 3;
            } else if (s.charAt(i) == 'T') {
                ret = ret * 10 + 4;
            }
        }
        return ret;
    }
}
