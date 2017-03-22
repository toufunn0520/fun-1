public class ZigzagIterator {
    Queue<Iterator> list ;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        list = new LinkedList<Iterator>();
        if(!v1.isEmpty()) list.offer(v1.iterator());
        if(!v2.isEmpty()) list.offer(v2.iterator());

    }
    public int next() {
        Iterator cur = list.poll();
        int val = (int)cur.next();
        if(cur.hasNext()) list.offer(cur);
        return val;
    }

    public boolean hasNext() {
        return (!list.isEmpty());
    }
}
