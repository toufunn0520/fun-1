class Solution {
public:
    vector<vector<bool>> map;
    void dfs(vector<vector<char>>& board, int l, int r){
        if (l <0 || r<0|| l>=board.size() || r>=board[0].size() || map[l][r] || board[l][r] == '.') {
            return;
        }
        map[l][r] = true;
        dfs(board, l+1,r);
        dfs(board, l,r+1);
        dfs(board, l-1,r);
        dfs(board, l,r-1);
    }
    
    int countBattleships(vector<vector<char>>& board) {
        if(board.empty()) return 0;
        int res = 0;
        map.resize(board.size(), vector<bool>(board[0].size(), false));
        for (int i= 0; i<board.size(); i++) {
            for (int j = 0; j< board[0].size(); j++) {
                if(board[i][j] == 'X' && !map[i][j]){
                    res++;
                    dfs(board, i, j);
                }
            }
        }
        return res;
    }
};
