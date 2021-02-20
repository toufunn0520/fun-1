/*

Time Complexity (Java): O(N \log N)O(NlogN), where NN is the number of events booked. For each new event, we search that the event is legal in O(\log N)O(logN) time, then insert it in O(1)O(1) time.

Time Complexity (Python): O(N^2)O(N 
2
 ) worst case, with O(N \log N)O(NlogN) on random data. For each new event, we insert the event into our binary tree. As this tree may not be balanced, it may take a linear number of steps to add each event.

Space Complexity: O(N)O(N), the size of the data structures used.
*/

class MyCalendar {
    TreeMap<Integer, Integer> inner;
    public MyCalendar() {
        inner = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Integer prev = inner.floorKey(start);
        Integer next = inner.ceilingKey(start);
        // no overlap
        if ((prev == null || inner.get(prev) <= start) && (next == null || next >= end ) ){
            inner.put(start, end);
            return true;
        }
            
        return false;
            
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
