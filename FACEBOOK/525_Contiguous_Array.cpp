class Solution {
public:
    int findMaxLength(vector<int>& nums) {
        int size = nums.size(); 
        int balanced = size;            //smart idea starting from size
        int balance[2*size+1] = {-1};   //key
        int maxsize = 0;
        for (int i = 0; i<size; i++) {
            balanced += nums[i] == 0 ? -1 : 1;
            if (balanced == size) {
                maxsize = i+1;          
            } else {
                if (balance[balanced] != -1) {    // if already seen
                    maxsize = max(maxsize, i-balance[balanced]);   (balance[balanced],i]
                } else {
                    balance[balanced] = i;  
                }
            }
            
        }
        return maxsize;
    }
};
