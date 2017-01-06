public class Logger {

    /** Initialize your data structure here. */
    Map<String, Integer> map;
    public Logger() {
        map = new HashMap<String, Integer>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(map.containsKey(message)){
            int expire = map.get(message);
            if(expire > timestamp) return false;
        }
        // whatever needs to update
        map.put(message, timestamp+10);
        return true;
        
    }
}
