package category.implementation.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangle {

    public static void main(String[] args) {
        List<List<Integer>> results = new PascalTriangle().generate(2);

        for (List<Integer> list : results) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }

    /**
     * [Leetcode 118] https://leetcode.com/problems/pascals-triangle/
     * 
     * <pre>
     * Given numRows, generate the first numRows of Pascal's triangle. For
     * example, given numRows = 5, Return
     *
     * [
     *              [1],
     *             [1,1],
     *            [1,2,1],
     *           [1,3,3,1],
     *          [1,4,6,4,1]
     * ]
     * </pre>
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();

        if (numRows <= 0) {
            return results;
        }

        List<Integer> first = Arrays.asList(1);
        results.add(first);
        for (int i = 2; i <= numRows; i++) {
            List<Integer> row = new ArrayList<Integer>();
            row.add(1);
            for (int j = 2; j <= i; j++) {
                if (j == i) {
                    row.add(1);
                    results.add(row);
                } else {
                    List<Integer> prev = results.get(results.size() - 1);
                    row.add(prev.get(j - 2) + prev.get(j - 1));
                }

            }
        }

        return results;
    }

    /**
     * [Leetcode 119] https://leetcode.com/problems/pascals-triangle-ii/
     * 
     * <pre>
     * Given an index k, return the kth row of the Pascal's triangle. For
     * example, given k = 3, Return [1,3,3,1]. Note: Could you optimize your
     * algorithm to use only O(k) extra space?
     * </pre>
     * 
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>(rowIndex + 1);

        if (rowIndex < 0) {
            return row;
        }

        row.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j >= 1; j--) {
                row.set(j, row.get(j - 1) + row.get(j));
            }
            row.add(1);
        }

        return row;
    }

}
