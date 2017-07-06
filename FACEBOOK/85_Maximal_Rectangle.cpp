/*Smart DP solution*/
class Solution {
public:
    int maximalRectangle(vector<vector<char>>& matrix) {
        if(matrix.empty() ) return 0;
        int rol = matrix.size();
        int col = matrix[0].size();
        int height[col];
        int right[col];
        int left[col];
        fill_n(left,col,0); fill_n(right,col,col); fill_n(height,col,0);  //good to remember
        int rectangle = 0;
        
        for (int i = 0; i<rol; i++) {
            int curleft = 0;
            int curright = col;
            for(int j = 0; j<col; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = max(curleft, left[j]);
                }else{
                    curleft = j+1;
                    left[j] = 0;
                }
            }
            
            for(int j = col-1; j>=0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = min(curright, right[j]);
                }else{
                    curright = j;
                    right[j] = col;
                }
            }
            
            for(int j = 0; j<col; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                }else{
                    height[j] = 0;
                }
            }
            
            //endof each line
            for(int j = 0; j<col; j++){
                rectangle = max(rectangle, height[j]*(right[j]-left[j]));
            }
        }
        return rectangle;
    }
};
