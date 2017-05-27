class Solution {
public:
    vector<int> numIslands2(int m, int n, vector<pair<int, int>>& positions) {
        vector<int> res;
        root = vector<int>(n*m, -1);
        vector<pair<int, int>> dir = {{1,0},{0,1},{-1,0},{0,-1}};
        int island = 0;
        for (auto p: positions) {
            int row = p.first;
            int col = p.second;
            int pos = row*n+col;
            root[pos] = pos;
            island ++;   //key
            
            for(auto nei: dir){
                int rnei = row + nei.first;
                int cnei = col + nei.second;
                int pnei = rnei*n+cnei;
                if (rnei < m && rnei >=0 && cnei <n && cnei >=0 && root[pnei] !=-1) {
                    int rootnei = findRoot(pnei);  //smart
                    int rootpos = findRoot(pos);   //smart
                    
                    if (rootnei != rootpos ){
                        root[rootpos] = rootnei;  //key
                        island --;    //key
                    }
                }
            }
            res.push_back(island);
        }
        return res;
    }
    
    vector<int> root;
    // compressed the path
    int findRoot(int pos) {
        while(root[pos] != pos) pos = root[root[pos]];
        return pos;
    }
};
