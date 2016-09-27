package category.search.backtracking;


public class WordSearch {

    public static void main(String[] args) {
        char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };

        System.out.println(new WordSearch().exist(board, "ABCB"));

    }

    /**
     * [Leetcode 79] https://leetcode.com/problems/word-search/
     *
     * <pre>
     * Given a 2D board and a word, find if the word exists in the grid. The
     * word can be constructed from letters of sequentially adjacent cell, where
     * "adjacent" cells are those horizontally or vertically neighboring. The
     * same letter cell may not be used more than once. For example,
     * 
     * Given board =
     * [
     *   ["ABCE"],
     *   ["SFCS"],
     *   ["ADEE"]
     * ]
     * 
     * word = "ABCCED", -> returns true,
     * word = "SEE", -> returns true,
     * word = "ABCB", -> returns false.
     * </pre>
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return false;
        }

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (search(board, word, j, i)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean search(char[][] board, String word, int startX, int startY) {
        if (word.length() == 0) {
            return true;
        }

        int rows = board.length;
        int cols = board[0].length;

        char current = word.charAt(0);
        if (startX >= cols || startX < 0 || startY < 0 || startY >= rows || board[startY][startX] != current) {
            return false;
        } else {
            board[startY][startX] = '*';
            String subWord = word.substring(1);
            if (search(board, subWord, startX + 1, startY) || search(board, subWord, startX, startY + 1)
                    || search(board, subWord, startX - 1, startY) || search(board, subWord, startX, startY - 1)) {
                return true;
            }
            board[startY][startX] = current;
        }
        return false;
    }

}
