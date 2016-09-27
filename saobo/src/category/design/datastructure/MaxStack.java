package category.design.datastructure;

public interface MaxStack {

    int peek();

    int peekMax();

    int pop();

    /**
     * Pop the max element in the stack.
     * 
     * @return
     */
    int popMax();

    void push(int val);
}
