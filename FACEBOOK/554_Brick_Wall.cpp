class Solution {
public:
    int leastBricks(vector<vector<int>>& wall) {
        unordered_map<int, int> map;
        int mmax = INT_MIN;
        
        for (auto it: wall) {
            int width = 0;
            for (int i =0; i<it.size()-1; i++) {  //! can not count the last one! other wise the min brick will be 0
                width+=it[i];
                map[width] ++;
                mmax= max(map[width], mmax);
                //printf("update %d into %d\n", width, mmax);
            }
        }
        
        //printf("final = %d  %d\n", wall.size(), mmax);
        return mmax==INT_MIN? wall.size(): wall.size()-mmax;  // key 
    }
};
