public class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] word = str.split(" ");
        if(word.length != pattern.length()){
            return false;
        } 
        Map index = new HashMap();
        for(Integer i =0; i<word.length ;i++)   //you can use Integer as the key of HashMap but you can't use int. Because an Object is needed.
            if( index.put(pattern.charAt(i), i) != index.put(word[i],i)) return false;
        }
        return true;
    }
}
