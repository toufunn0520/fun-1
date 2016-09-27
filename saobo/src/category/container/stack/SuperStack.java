package category.container.stack;

public interface SuperStack<T> {

    void inc(int num, int numToAdd);

    T peek();

    T pop();

    void push(T val);

}
