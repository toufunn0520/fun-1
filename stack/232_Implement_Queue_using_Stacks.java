class MyQueue {
    // Push element x to the back of queue.
    private Stack<Integer> first  =new Stack<Integer>();
    private Stack<Integer> second = new Stack<Integer>();
    
    public void push(int x) {
        first.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        // get all elements from first
        if(second.empty()){
            while(!first.empty()) {
                second.push(first.pop());
            }
        }
        second.pop();
        return;
    }

    // Get the front element.
    public int peek() {
         if(second.empty()){
            while(!first.empty()) {
                second.push(first.pop());
            }
        }
        return second.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        if(second.empty() && first.empty()) return true;
        return false;
        
    }
}
