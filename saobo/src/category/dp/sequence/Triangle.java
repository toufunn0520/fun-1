package category.dp.sequence;

import java.util.List;

public class Triangle {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * [Leetcode 120] https://leetcode.com/problems/triangle/
     * 
     * <pre>
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
     * 
     * For example, given the following triangle
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     * </pre>
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int[] path = new int[triangle.size() + 1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                path[j] = Math.min(triangle.get(i).get(j) + path[j], triangle.get(i).get(j) + path[j + 1]);
            }
        }

        return path[0];
    }

}
