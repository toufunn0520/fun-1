class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        if(matrix.empty()) return false;
        int row = matrix.size();
        int col = matrix[0].size();
        int r = row-1;
        int c = 0;
        
        while(r<row && r>=0 && c<col &&c>=0) {
            if(matrix[r][c] == target) return true;
            if(matrix[r][c] > target) {
                r--;
                continue;
            }else{
                c++;
            }
        }
        return false;
    }
};
