package category.implementation.iterator;

import java.util.Iterator;
import java.util.List;

import category.linkedlist.ListNode;

public class AggregatedIterator implements IteratorInterface<ListNode> {

    Iterator<ListNode> iter;

    Iterator<Iterator<ListNode>> iterOfIterators;

    public AggregatedIterator(List<Iterator<ListNode>> iters) {
        if (iters == null) {
            return;
        }

        iterOfIterators = iters.iterator();
    }

    @Override
    public boolean hasNext() {
        while (iter == null || !iter.hasNext()) {
            if (iterOfIterators != null && iterOfIterators.hasNext()) {
                iter = iterOfIterators.next();
            }
        }

        if (iter == null || !iter.hasNext()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ListNode next() {
        if (hasNext()) {
            return iter.next();
        } else {
            return null;
        }
    }

}
