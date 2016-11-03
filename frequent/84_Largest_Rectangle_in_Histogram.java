public class Solution {
    
    public int largestRectangleArea(int[] heights) {
       if (heights == null || heights.length == 0) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<Integer>();  
        int max = 0;
        for (int i = 0; i <= heights.length; i++) {
            int curt = (i == heights.length) ? -1 : heights[i];
            while (!stack.isEmpty() && curt <= heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }
        
        return max;
        
    }
}
