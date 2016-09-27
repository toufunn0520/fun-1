package category.implementation.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Google: write an iterator iterate two lists take turns.
 */
public class ZigZagIterator implements IteratorInterface<Integer> {

    private int currentIteratorIndex;

    private List<Iterator<Integer>> iterators;

    public ZigZagIterator(List<List<Integer>> lists) {
        if (lists == null) {
            throw new RuntimeException("input lists canot be null");
        }

        iterators = new ArrayList<Iterator<Integer>>(lists.size());

        for (List<Integer> list : lists) {
            iterators.add(list.iterator());
        }

        currentIteratorIndex = -1;
    }

    @Override
    public boolean hasNext() {
        for (Iterator<Integer> iterator : iterators) {
            if (iterator.hasNext()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new RuntimeException("no next element");
        }

        int nextIteratorIndex = ++currentIteratorIndex > (iterators.size() - 1) ? 0 : currentIteratorIndex;

        while (!iterators.get(nextIteratorIndex).hasNext()) {
            if (nextIteratorIndex == iterators.size() - 1) {
                nextIteratorIndex = 0;
            } else {
                nextIteratorIndex++;
            }
        }

        currentIteratorIndex = nextIteratorIndex;
        return iterators.get(currentIteratorIndex).next();
    }

}
