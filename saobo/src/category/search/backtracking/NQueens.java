package category.search.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

    public static void main(String[] args) {
        List<String[]> result = new NQueens().solveNQueens(4);

        for (String[] sol : result) {
            for (int i = 0; i < sol.length; i++) {
                System.out.println(sol[i]);
            }

            System.out.println("~~~~~~~~");
        }
    }

    private void solveNQ(int n, int irow, List<Integer> col, List<char[]> solution, List<String[]> allSolutions) {
        if (irow == n) {
            String[] sol = new String[solution.size()];
            for (int i = 0; i < solution.size(); i++) {
                sol[i] = new String(solution.get(i));
            }
            allSolutions.add(sol);
            return;
        }

        for (int icol = 0; icol < n; icol++) {
            if (validPosition(col, irow, icol)) {
                char[] chars = new char[n];
                Arrays.fill(chars, '.');
                chars[icol] = 'Q';
                solution.add(chars);
                col.add(icol);
                solveNQ(n, irow + 1, col, solution, allSolutions);
                solution.remove(solution.size() - 1);
                col.remove(col.size() - 1);
            }
        }

    }

    /**
     * [Leetcode 51] https://leetcode.com/problems/n-queens/
     * 
     * <pre>
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
     *
     * Given an integer n, return all distinct solutions to the n-queens puzzle.
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen
     * and an empty space respectively.
     *
     * For example,
     * There exist two distinct solutions to the 4-queens puzzle:
     *
     * [
     *  [".Q..",  // Solution 1
     *   "...Q",
     *   "Q...",
     *   "..Q."],
     *
     *  ["..Q.",  // Solution 2
     *   "Q...",
     *   "...Q",
     *   ".Q.."]
     * ]
     * </pre>
     *
     * @param n
     * @return
     */
    public List<String[]> solveNQueens(int n) {
        List<String[]> allSolutions = new ArrayList<String[]>();
        List<char[]> solution = new ArrayList<char[]>();
        List<Integer> col = new ArrayList<Integer>();
        solveNQ(n, 0, col, solution, allSolutions);

        return allSolutions;
    }

    private boolean validPosition(List<Integer> col, int irow, int icol) {
        if (irow < col.size()) {
            return false;
        }

        for (int i = 0; i < col.size(); i++) {
            if (icol == col.get(i) || Math.abs(irow - i) == Math.abs(icol - col.get(i))) {
                return false;
            }
        }
        return true;
    }
}
