public class MinStack {
    private Stack<Integer> min;
    private Stack<Integer> res;

    /** initialize your data structure here. */
    public MinStack() {
        min = new Stack<Integer>(); 
        res = new Stack<Integer>(); 
    }
    public void push(int x) {
        if(min.empty()){
            min.push(x);
        }else {
            if(min.peek()<= x) {
                min.push(min.peek());
            }else {
                min.push(x);
            }
        }
        res.push(x);
    }
    
    public void pop() {
        min.pop();
        res.pop();
    }
    
    public int top() {
        return res.peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
