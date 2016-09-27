package category.implementation.matrix;

import java.util.Arrays;

public class ValidSudoku {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * [Leetcode 36] https://leetcode.com/problems/valid-sudoku/
     * 
     * <pre>
     * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
     * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
     * A partially filled sudoku which is valid.
     *
     * Note:
     * A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
     * </pre>
     *
     * @param board
     * @return
     */
    public boolean isValid(char[][] board) {

        if (board == null || board.length != 9) {
            return false;
        }

        boolean[] numbers = new boolean[9];

        for (int i = 0; i < board.length; i++) {
            if (board[i].length != 9) {
                return false;
            }

            Arrays.fill(numbers, false);
            for (int j = 0; j < board[i].length; j++) {
                if (numbers[board[i][j]] == true) {
                    return false;
                }
                numbers[board[i][j]] = true;
            }
        }

        for (int j = 0; j < 9; j++) {
            Arrays.fill(numbers, false);
            for (int i = 0; i < 9; i++) {
                if (numbers[board[i][j]] == true) {
                    return false;
                }
                numbers[board[i][j]] = true;
            }
        }

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                Arrays.fill(numbers, false);
                for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 3; n++) {
                        if (numbers[board[i + m][j + n]] == true) {
                            return false;
                        }
                        numbers[board[i + m][j + n]] = true;
                    }
                }
            }
        }

        return true;
    }
}
