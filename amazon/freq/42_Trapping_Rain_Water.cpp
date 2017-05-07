class Solution {
public:
    int trap(vector<int>& height) {
        int sum = 0;
        int left  = 0;
        int right = height.size()-1;
        int leftmax = INT_MIN;
        int rightmax = INT_MIN;
        while(left<right){   
            if(height[left]<=height[right]){
                if( height[left] > leftmax) {
                    leftmax = height[left++];
                }else{
                    sum += leftmax-height[left++];
                }
            }else{
                if( height[right] > rightmax) {
                    rightmax = height[right--];
                }else{
                    sum += rightmax - height[right--];
                }
            }
            
        }
        return sum;    
    }
};
