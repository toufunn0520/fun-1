package category.implementation.iterator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import category.linkedlist.ListNode;

public class DeduplicatedIterator implements IteratorInterface<ListNode> {

    private Iterator<ListNode> iter;

    private Queue<ListNode> nextQueue;

    private Set<ListNode> set;

    public DeduplicatedIterator(Iterator<ListNode> iter) {
        this.iter = iter;
        this.nextQueue = new LinkedList<ListNode>();
        set = new HashSet<ListNode>();

        if (iter.hasNext()) {
            ListNode next = iter.next();
            nextQueue.offer(next);
            set.add(next);
        }
    }

    @Override
    public boolean hasNext() {
        if (!nextQueue.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ListNode next() {
        ListNode next = null;
        while (iter.hasNext()) {
            next = iter.next();
            if (!set.contains(next)) {
                nextQueue.offer(next);
                set.add(next);
                break;
            }
        }

        return nextQueue.poll();
    }

}
