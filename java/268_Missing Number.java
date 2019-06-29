class Solution {
    public int missingNumber(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
    
        int expect = 0;
        int real = 0;
        
        for (int i = 0; i < length; i++) {
            real ^= nums[i];
        }
        
        for (int i = 0 ; i < length + 1; i++) {
            expect ^= i;
        }
        return (expect ^ real);
    }
}
