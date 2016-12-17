public class TwoSum {

    // Add the number to an internal data structure.
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();  
    public void add(int number) {
	    if(map.containsKey(number)){
	        map.put(number, map.get(number)+1);
	    }else{
	        map.put(number, 1);
	    }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    for(int key : map.keySet()) {
	        int other = value-key;
	        if ((key == other && map.get(other) > 1) || (other != key && map.containsKey(other))) {
	            return true;
	        }
        }
	    return false;
	}
}
