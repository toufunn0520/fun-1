class Solution {
public:
    int maxKilledEnemies(vector<vector<char>>& grid) {
        int row = grid.size();
        int col = row? grid[0].size(): 0; // key edge case!
        int rowhit = 0;
        int colhit[col+1];  // key if the col is 0! 
        int res = 0;
        
        for (int i = 0; i<row; i++) {
            for (int j = 0; j<col; j++) {
                if ( !j || grid[i][j-1] == 'W' ) {
                    rowhit = 0;                                // reset counter
                    for (int k = j; k<col && grid[i][k] != 'W'; k++) {
                        if(grid[i][k] == 'E') rowhit++;
                    }
                }
                if ( !i || grid[i-1][j] == 'W' ) {
                    colhit[j] = 0;    // reset counter
                    for (int k = i; k<row && grid[k][j]!= 'W'; k++) {
                        if(grid[k][j] == 'E') colhit[j]++;
                    }
                    
                }
                if (grid[i][j] == '0') res = max(res, (rowhit + colhit[j]));
            }
        }
        return res;
    }
};
