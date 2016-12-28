public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();  // key empty list
        if(strings == null || strings.length == 0) return res;
        
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strings){
            String base = getString(s);
            if(!map.containsKey(base)){
                map.put(base, new ArrayList<>());
            }
            map.get(base).add(s);
        }
        for(String s:  map.keySet()){
            Collections.sort(map.get(s));
        }
        res = new ArrayList<List<String>>(map.values());
        return res;
    }
    private String getString(String s){
        if(s ==null || s.length() ==0){
            return s;
        }
        StringBuilder base = new StringBuilder();
        int shift = s.charAt(0)-'a';
        for(int i =0; i<s.length(); i++){
            char c = s.charAt(i);
            int curshift = c-'a'-shift;
            if(curshift <0) curshift +=26;
            c = (char)('a' + curshift);
            base.append(c);
        }
        return base.toString();
    }
}
