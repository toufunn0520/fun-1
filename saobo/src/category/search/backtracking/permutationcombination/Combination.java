package category.search.backtracking.permutationcombination;

import java.util.ArrayList;
import java.util.List;

public class Combination {

    public static void main(String[] args) {
        List<List<Integer>> result = new Combination().combine(4, 2);

        for (List<Integer> list : result) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }

    private void combinationHelper(int n, int k, int[] visited, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<Integer>(current));
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] == 1) {
                continue;
            }

            current.add(i + 1);
            visited[i] = 1;
            combinationHelper(n, k, visited, current, result);
            current.remove(current.size() - 1);
            visited[i] = 0;
        }
    }

    /**
     * [Leetcode 77] https://leetcode.com/problems/combinations/
     * 
     * <pre>
     * Given two integers n and k, return all possible combinations of k numbers
     * out of 1 ... n. For example, If n = 4 and k = 2, a solution is: [ [2,4],
     * [3,4], [2,3], [1,2], [1,3], [1,4], ]
     * </pre>
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (n == 0) {
            return result;
        }

        int[] visited = new int[n];
        List<Integer> current = new ArrayList<Integer>();
        combinationHelper(n, k, visited, current, result);

        return result;
    }

}
