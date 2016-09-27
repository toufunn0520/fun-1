package category.implementation.iterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [Leetcode 284] https://leetcode.com/problems/peeking-iterator/
 * 
 * <pre>
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that
 * support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
 * Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
 * 
 * Call next() gets you 1, the first element in the list.
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 * You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.
 * </pre>
 */
class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;

    private Queue<Integer> queue;

    public PeekingIterator(Iterator<Integer> iterator) {
        queue = new LinkedList<Integer>();
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty() || iterator.hasNext();
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (!queue.isEmpty()) {
            return queue.poll();
        } else {
            return iterator.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        Integer peek = null;

        if (!queue.isEmpty()) {
            peek = queue.peek();
        } else {
            peek = iterator.next();
            queue.offer(peek);
        }

        return peek;
    }
}
