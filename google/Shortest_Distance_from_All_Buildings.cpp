class Solution {
public:
    
    int shortestDistance(vector<vector<int>>& grid) {
        int row = grid.size();
        int col = row?grid[0].size():0;
        int building = 0;
        int res = INT_MAX;
        vector<vector<int>>num(row, vector<int>(col, 0));
        vector<vector<int>>distance(row, vector<int>(col, 0));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1){
                    vector<vector<int>>visited(row, vector<int>(col, 0));
                    int curdis = 0;
                    queue<pair<int, int>> curlevel, nextlevel;
                    building++;
                    curlevel.emplace(i, j);
                    while (!curlevel.empty()) {
                        curdis++;
                        while(!curlevel.empty()) {
                            auto it = curlevel.front();
                            int currow = it.first;
                            int curcol = it.second;
                            curlevel.pop();
                            
                            vector<pair<int, int>> trynext = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                            for (auto tmp:trynext) {
                                int tryrow = currow+tmp.first;
                                int trycol = curcol+tmp.second;
                                
                                if (tryrow >=0 && tryrow<row && trycol>=0 && trycol<col && !visited[tryrow][trycol] && grid[tryrow][trycol] == 0) {
                                    distance[tryrow][trycol]+= curdis;
                                    num[tryrow][trycol]++;
                                    nextlevel.emplace(tryrow, trycol);
                                    visited[tryrow][trycol] = true;
                                }
                            }
                        }
                        swap(curlevel,nextlevel);
                    }
                    
                }
            }
        }
        
        for(int i = 0; i< row; i++) {
            for (int j = 0; j<col; j++) {
                if(num[i][j]== building && distance[i][j] < res){
                    res = distance[i][j];
                }
            }
        }
        return res==INT_MAX?-1:res;  !!key
    }
};
