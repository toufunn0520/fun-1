/*Once it beggan to increase it means going right. so need to save root value for min*/
bool verifyPreorder(int* preorder, int preorderSize) {
    /*Try wit*/
    if( !preorder || !preorderSize) return true;
    int min = INT_MIN;
    int i = -1;
    
    for (int j = 0; j< preorderSize; j++) {
        if (preorder[j] < min) {
            return false;
        }
        while(i>=0 && preorder[j] > preorder[i]){
            /*Keep updating min 每pop一次相当于i--*/
            min = preorder[i--];
        }
        /*Push*/
        preorder[++i] = preorder[j];
    }
    return true;
}

/*verify PostOrder*/
/*Once it beggan to increate it means going from left to root. so need to save left value for max!!*/

    public boolean IsValidPostOrderBst(int[] nums)
    {
        int i = nums.length;
        int max = Integer.MAX_VALUE;
        for (int j = nums.length - 1; j >= 0; j--)
        {
            if (nums[j] > max) return false;
            while (i <= nums.length - 1 && nums[j] > nums[i])
                max = nums[i++];
            nums[--i] = nums[j];
        }
        return true;
    }
