class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        int length = grid.length; 
        if (length == 0) return 0; // sanity check
        int width = grid[0].length;
        int res = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 0 ; j < width; j++) {
                if (grid[i][j] == '1') {
                    updateNeighbor(i, j, length, width, grid);
                    res ++;
                }
            }
        }
        return res;
    }
    
    void updateNeighbor(int i, int j, int maxi, int maxy, char[][]grid) {
        // reached boundary or zeros
        if ( i < 0 || i >= maxi || j < 0 || j >= maxy || grid[i][j] == '0' ) {
            return;
        }
        grid[i][j] = '0';
        
        // four direction
        updateNeighbor(i+1, j, maxi, maxy, grid);
        updateNeighbor(i-1, j, maxi, maxy, grid);
        updateNeighbor(i, j+1, maxi, maxy, grid);
        updateNeighbor(i, j-1, maxi, maxy, grid);
        return;
    }
    
}
