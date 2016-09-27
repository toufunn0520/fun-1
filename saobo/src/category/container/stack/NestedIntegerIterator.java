package category.container.stack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import category.implementation.iterator.IteratorInterface;

public class NestedIntegerIterator implements IteratorInterface<Integer> {

    private Iterator<NestedInteger> current;

    private Stack<Iterator<NestedInteger>> stack;

    /**
     * Iterator iterates nested integer. i.e <1,2, < <3,4>, 5, < 6, <7> > >, 8> should return 1,2,3,4,5,6,7,8
     * 
     * @param nestedInteger
     */
    public NestedIntegerIterator(NestedInteger nestedInteger) {
        if (nestedInteger == null) {
            throw new RuntimeException("invalid input");
        }

        if (nestedInteger.isInteger()) {
            List<NestedInteger> head = new LinkedList<NestedInteger>();
            head.add(nestedInteger);
            current = head.iterator();
        } else {
            current = nestedInteger.getList().iterator();
        }

        this.stack = new Stack<Iterator<NestedInteger>>();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty() || current.hasNext();
    }

    @Override
    public Integer next() {
        NestedInteger currentNestedInteger = null;
        while (!current.hasNext()) {
            if (stack.isEmpty()) {
                throw new RuntimeException("no next");
            }
            current = stack.pop();
        }

        currentNestedInteger = current.next();

        while (!currentNestedInteger.isInteger()) {
            stack.push(current);
            current = currentNestedInteger.getList().iterator();
            currentNestedInteger = current.next();
        }

        return currentNestedInteger.getInteger();
    }
}
