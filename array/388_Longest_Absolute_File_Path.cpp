class Solution {
public:
    int lengthLongestPath(string input) {
        int res =0;
        vector<int> dir(256,0) ;//in fs max is 256, we only care the length+ depth
        input = input + '\n';  // last
        int depth = 0;
        bool isFile = false;
        int len = 0;
        for (int i = 0 ; i < input.size() ; i++) {
            switch (input[i]){
                case '\n': isFile = false; len = 0; depth = 0;break;
                case '\t': depth ++; break;
                case '.': isFile = true; // needs to update '.'
                default:
                    len++;
                    dir[depth] = len;  // continues update
                    if (isFile) res = max(res, accumulate(dir.begin(), dir.begin()+depth+1,0)+depth); // need to add '/'
                    
            }
        }
        return res;
    }
};
