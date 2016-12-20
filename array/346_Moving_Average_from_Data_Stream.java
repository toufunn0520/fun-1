public class MovingAverage {

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        this.q=new LinkedList<Integer>();
        sum = 0;
    }
    
    public double next(int val) {
        if (q.size() >= this.size) {
            sum =  sum - q.peek(); 
            q.poll();
            
        }
        q.offer(val);
        sum += val;
        return (double)(sum / q.size());
    }
    
    Queue<Integer> q;
    double sum;
    int size;
}
/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
