/*recursively find all*/
void dfs(char** grid, int x, int y, int maxx, int maxy){
    if (x<0 || y <0 || x>=maxx || y>=maxy) {
        return;
    }
    if(grid[x][y]== '1') {
        grid[x][y] = '0';
        dfs(grid, x-1,y, maxx, maxy);
        dfs(grid, x,y-1, maxx, maxy);
        dfs(grid, x+1,y, maxx, maxy);
        dfs(grid, x,y+1, maxx, maxy);
    }
    return;
}

int numIslands(char** grid, int gridRowSize, int gridColSize) {
    if (!grid || (!gridRowSize && !gridColSize)) return 0;
    int res = 0;
    for (int i = 0; i<gridRowSize; i++) {
        for (int j = 0; j<gridColSize; j++) {
            if (grid[i][j] == '1') {
                res++;
                dfs(grid, i, j, gridRowSize, gridColSize);
            }
        }
    }
    return res;
}


class Solution {
public:
    //mark connected to 0
    void dfs(vector<vector<char>>& grid, int i , int j){
        int col = grid[0].size();
        int row = grid.size();
        if(i<0 || i>=row || j<0 || j>=col) return;
        if (grid[i][j] == '1') {
            grid[i][j] = '0';
            dfs(grid, i-1,j);
            dfs(grid, i,j-1);
            dfs(grid, i,j+1);
            dfs(grid, i+1,j);
        }
    }
    
    int numIslands(vector<vector<char>>& grid) {
        int res = 0;
        //if(grid == nullptr  && grid.empty()) return res;
        if(grid.empty()) return res;

        int col = grid[0].size();
        int row = grid.size();
        
        for (int i = 0; i<row; i++) {
            for (int j = 0; j<col; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }
};
