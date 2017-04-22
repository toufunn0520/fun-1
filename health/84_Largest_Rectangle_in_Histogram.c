class Solution {
public:
    int largestRectangleArea(vector<int>& heights) {
        int max = 0;
        /*Index array*/
        vector<int> index;
        for(int i =0; i<heights.size(); i++){
            while(index.size() && heights[index.back()] >= heights[i]){
                int h = heights[index.back()];
                index.pop_back();
                //int left = index[index.size()-1];!!!
                int left = index.size() > 0 ? index.back() : -1;
                if ((i-left-1)*h > max) max = (i-left-1)*h;
            }
            index.push_back(i);
        }
        int i = heights.size();
        while(index.size()){
            int h = heights[index.back()];
            index.pop_back();
            int left = index.size() > 0 ? index.back() : -1;
            if ((i-left-1)*h > max) max = (i-left-1)*h;
           //printf("hight=%d width=%d left=%d\n", h,i-left-1, left);
        }
        return max;
    }
};
