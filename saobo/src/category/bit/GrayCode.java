package category.bit;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {

    public static void main(String[] args) {
        System.out.println(new GrayCode().grayCode(3));
    }

    /**
     * [Leetcode 89] https://leetcode.com/problems/gray-code/
     * 
     * <pre>
     * generate 0, 1 then add 10 from back to get 11, 10
     * same goes for 00, 01, 11, 10, add 100 to get 110, 111, 101, 100
     * </pre>
     */
    public List<Integer> grayCode(int n) {
        List<Integer> results = new ArrayList<Integer>();
        results.add(0); // starts from 0
        for (int i = 0; i < n; i++) {
            int inc = 1 << i; // move 1 i times
            for (int j = results.size() - 1; j >= 0; j--) { // backtracking
                results.add(results.get(j) + inc);
            }
        }
        return results;
    }
}
