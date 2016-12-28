public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if(strs == null || strs.length ==0 ) return res;
        Map<String, List<String>> map = new HashMap<>();
        
        for(String s: strs){
            char[] tmp = s.toCharArray();
            Arrays.sort(tmp);
            String key = String.valueOf(tmp);
            if(!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }
        for(String s: map.keySet()){
            Collections.sort(map.get(s));
        }
        res = new ArrayList<List<String>>(map.values());
        return res;
    }
}
