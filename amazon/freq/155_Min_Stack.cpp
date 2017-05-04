class MinStack {
public:
    /** initialize your data structure here. */
    stack<int> order;
    stack<int> mini;
    MinStack() {
    }
    
    void push(int x) {
        if (mini.empty() || (mini.top() >= x)) mini.push(x);
        order.push(x);
        
    }
    
    void pop() {
        if(mini.top()==order.top()) mini.pop();
        order.pop();
    }
    
    int top() {
        return order.top();
    }
    
    int getMin() {
        return mini.top();
    }
};
