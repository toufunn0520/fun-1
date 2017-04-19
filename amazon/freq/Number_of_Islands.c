void find(char** grid, int R, int C, int MaxR, int MaxC){
    if(!grid || R<0 || C<0 || R>=MaxR || C>=MaxC) return;
    if(grid[R][C] == '1'){
        grid[R][C] = '0';
        find(grid, R-1, C, MaxR, MaxC);
        find(grid, R+1, C, MaxR, MaxC);
        find(grid, R, C-1, MaxR, MaxC);
        find(grid, R, C+1, MaxR, MaxC);
    }
    return;
}
int numIslands(char** grid, int gridRowSize, int gridColSize) {
    if(!grid || !gridRowSize || !gridColSize) return 0;
    int result = 0;
    for(int i=0; i<gridRowSize; i++){
        for(int j =0; j<gridColSize; j++){
            if(grid[i][j] == '1'){
                result ++;
                find(grid,i,j,gridRowSize,gridColSize);
            }
        }
    }
    return result;
}
