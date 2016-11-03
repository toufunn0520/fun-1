class MyStack {
    // Push element x onto stack. PriorityQueue methods
    private Queue<Integer> q1 = new PriorityQueue<Integer>();
    private Queue<Integer> q2 = new PriorityQueue<Integer>();
    private Queue<Integer> tmp ;
    public void push(int x) {
        q1.offer(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        while(q1.size()>1){
            q2.offer(q1.poll());
        }
        q1.poll();
        // swap
        tmp = q1;
        q1 = q2;
        q2 = tmp;
        return;
    }

    // Get the top element.
    public int top() {
           while(q1.size()>1){
            q2.offer(q1.poll());
        }
        int result = q1.peek();
        if(!q1.isEmpty())  q2.offer(q1.poll());
        
        tmp = q1;
        q1 = q2;
        q2 = tmp;
        
        return result;
        
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q1.isEmpty();
    }
}
