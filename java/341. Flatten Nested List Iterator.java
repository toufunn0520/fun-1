/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
 
 
 // Use Stack to push last item to the stack and recusively find none list item, i.e an integer.
 // Don't forget to pop items out
 
public class NestedIterator implements Iterator<Integer> {

    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new Stack();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            queue.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        NestedInteger next = queue.pop();
        return next.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!queue.isEmpty()) {
            final NestedInteger cur = queue.peek();
            if (cur.isInteger()) {
                return true;
            } else {
                queue.pop(); // I forget to pop then unested
                List<NestedInteger> list = cur.getList();
                int length = list.size();
                for (int i = length-1; i>=0; i--) {
                    queue.push(list.get(i));
                }
            }
            
        }
        return false;
    }
    
    Stack<NestedInteger> queue;
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
