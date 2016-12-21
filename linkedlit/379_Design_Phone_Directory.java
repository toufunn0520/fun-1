public class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    Set<Integer> used;
    Queue<Integer> avail;
    int max;
    public PhoneDirectory(int maxNumbers) {
        this.avail = new LinkedList<Integer>();
        this.used = new HashSet<Integer>();
        this.max = maxNumbers;
        for(int i =0; i<maxNumbers; i++){
            avail.offer(i);
        }
        
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(!avail.isEmpty()){
            used.add(avail.peek());
            return avail.poll();
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(number >= max || number<0) return false;
        if(used.contains(number)) return false;
        return true;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(used.contains(number)){
            used.remove(number);
            avail.offer(number);
        }
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
