bool helper(char** board, int x, int y, int row, int col, char* word, int i){
    if(i== strlen(word)) {
        return true;}
    if( x<0 || x>=row || y<0 || y>=col) return false;
    if(board[x][y] != word[i]){
        return false;
    }
    char tmp =board[x][y] ;
    board[x][y] ='#'; // to prevent the loop
    bool exist  = helper(board, x-1,y,row,col,word, i+1) ||  helper(board, x,y-1,row,col,word, i+1) ||  helper(board, x+1,y,row,col,word, i+1) ||  helper(board, x,y+1,row,col,word, i+1);
    board[x][y] = tmp; // to prevent the loop
    return exist ;
}

bool exist(char** board, int boardRowSize, int boardColSize, char* word) {
    for(int i =0; i<boardRowSize; i++){
        for(int j =0; j<boardColSize; j++){
            if(helper(board, i, j, boardRowSize, boardColSize,word, 0)){
                return true;
            }
        }
    }
    
    return false;
}
