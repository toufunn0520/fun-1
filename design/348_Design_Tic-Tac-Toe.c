typedef struct {
    int* row;
    int* col;
    int anti;
    int diagnol;
    int size;
} TicTacToe;

/** Initialize your data structure here. */
TicTacToe* ticTacToeCreate(int n) {
    TicTacToe* tic = malloc(sizeof(TicTacToe));
    tic->row = malloc(sizeof(int)*n);
    memset(tic->row, 0,sizeof(int)*n);
    tic->col = malloc(sizeof(int)*n);
    memset(tic->col, 0,sizeof(int)*n);
    tic->anti = 0;
    tic->diagnol = 0;
    tic->size = n;
    return tic;
}

/** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
int ticTacToeMove(TicTacToe* obj, int row, int col, int player) {
    /*No need to valid move*/
    if (!obj) return 0;
    if (player == 1) {
        (obj->row[row])++;
        (obj->col[col])++;
        if((row+col) == (obj->size-1)) obj->anti++;
        if(row == col) obj->diagnol++;
        if ((obj->row[row] == obj->size) ||(obj->col[col] == obj->size) || obj->diagnol == obj->size || obj->anti == obj->size) {
            return 1;
        }
    } else {
        (obj->row[row])--;
        (obj->col[col])--;
        if((row+col) == (obj->size-1)) obj->anti--;
        if(row == col) obj->diagnol--;
        if ((obj->row[row] == -(obj->size)) ||(obj->col[col] == -(obj->size))|| obj->diagnol == -(obj->size) || obj->anti == -(obj->size)) {
            return 2;
        }
    }
    return 0;
}

void ticTacToeFree(TicTacToe* obj) {
    if(obj){
        free(obj->row);
        free(obj->col);
    }
    obj = NULL;
    return;
}
