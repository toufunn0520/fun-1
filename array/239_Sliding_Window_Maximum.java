/*
考虑了很久，最后求助了Discuss。原理使用一个Deque，或者用doubly linkedlist也可以。
我们队这个数组维护一个递减的双端队列，队列的内容为坐标index。
每次移动时，移除小于当前队首坐标的元素，同时比较当前元素与队尾元素的大小，假如队尾元素较小，移除队尾元素，继续比较当前元素和队尾。
比较完毕后把当前元素加入到队列中， 最后判断是否窗口已满，要输出到结果集中。
*/
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return new int[]{};
        int len = nums.length;
        int[] ans =new int [len-k+1];
        Deque<Integer> dq = new LinkedList<>();
        
        for(int i =0; i<len; i++){
            //Check if MAX needs to be removed//
            while (!dq.isEmpty() && dq.peekFirst()<(i-k+1)) {
                dq.pollFirst();  //key while
            }
            
            //Insert nums[i] and keep decending order keep [MAX, nums[i]] inside dqueue.
            while( !dq.isEmpty() && (nums[dq.peekLast()] < nums[i])) dq.pollLast();
            
            dq.offerLast(i);
           
            // Once it filled with more than K items
            if(i>=k-1) ans[i-k+1] = nums[dq.peekFirst()];
        }
        return ans;
    }
}
