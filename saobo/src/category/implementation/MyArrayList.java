package category.implementation;

import java.util.Arrays;

/**
 * [Snapchat] implement ArrayList
 *
 * @param <T>
 */
public class MyArrayList<T> {

    public static Object[] EMPTY_DATA = {};

    public static void main(String[] args) {
        MyArrayList<Integer> a = new MyArrayList<Integer>();
        for (int i = 0; i < 90; i++)
            a.add(i);
        a.set(87, 123);
        System.out.println(a.get(87));
        a.remove(87);
        System.out.println(a.get(87));
        System.out.println(a.size());

    }

    private Object[] data;

    private int DEFAULT_SIZE = 10;

    private int size;

    public MyArrayList() {
        this.data = EMPTY_DATA;
        size = 0;
    }

    public boolean add(T element) {
        ensureSize(size + 1);

        data[size++] = element;
        return true;
    }

    private void ensureSize(int size) {
        if (size > data.length) {
            growSize(Math.max(size, DEFAULT_SIZE));
        }
    }

    public T get(int index) {
        indexRangeCheck(index);
        return (T) data[index];
    }

    private void growSize(int size) {
        // may do stack overflow check

        int oldSize = data.length;
        int newSize = oldSize + oldSize >> 1;
        newSize = Math.max(newSize, size);

        data = Arrays.copyOf(data, newSize);
    }

    private void indexRangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("index range is not valid");
        }
    }

    public T remove(int index) {
        indexRangeCheck(index);

        T oldData = (T) data[index];

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(data, index + 1, data, index, numMoved);
        data[--size] = null;

        return oldData;
    }

    public void set(int index, T element) {
        indexRangeCheck(index);

        data[index] = element;
    }

    public int size() {
        return size;
    }
}
