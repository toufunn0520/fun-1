bool isValidSudoku(char** board, int boardRowSize, int boardColSize) {
    if(!board || (boardRowSize%3) || (boardColSize%3)) return false;
    bool res[10] = {0};
    
    // verify each row
    for(int i =0; i<boardRowSize; i++){
        memset(res, 0, 10);
        for(int j =0; j<boardColSize; j++){
            if(board[i][j] == '.')continue;
            if(board[i][j] > '9' || board[i][j]<'0') return false;
            int num = (int)(board[i][j] -'0');
            if(res[num]) return false;
            res[num] = true;
        }
    }
   // printf("OK 1\n");
    // verify each col
    for(int i =0; i<boardColSize; i++){
        memset(res, 0, 10);
        for(int j =0; j<boardRowSize; j++){
            if(board[j][i] == '.')continue;
            if(board[j][i] > '9' || board[j][i]<'0') return false;
            int num = (int)(board[j][i] -'0');
            if(res[num]) return false;
            res[num] = true;
        }
    }
    //printf("OK 2\n");
    // verify each 3*3 no need to check validation of the value
     for(int i =0; i<boardRowSize; i=i+3){
        for(int j =0; j<boardColSize; j=j+3){
            memset(res, 0, 10);
            for(int k = i; k<i+3; k++){
              for(int l = j; l<j+3; l++){
                  if(board[k][l] == '.')continue;
                  int num = (int)(board[k][l] -'0');
                  if(res[num]) return false;
                  res[num] = true;
              }
            }
        }
    }
    
    return true;
    
}
