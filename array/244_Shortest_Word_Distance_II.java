
/*我们可以维护一个HashMap<String, ArrayList<Integer>>，map里面存储每个word以及出现的坐标index。这样查询的时候我们只需要get这两个单词的list，然后进行比较就可以了。比较的时候， 因为两个list都是排序后的， 所以我们可以用类似merge two sorted list的方法来计算minDistance。*/
public class WordDistance {
    Map<String, ArrayList<Integer>> map;
    public WordDistance(String[] words) {
        this.map= new HashMap<>();
        for(int i =0; i<words.length; i++){
            if(map.containsKey(words[i])) {
                map.get(words[i]).add(i);
            }else{
                map.put(words[i], new ArrayList<Integer>(Arrays.asList(i)));   
            }
        }
    }
    /*Hidden info  each list is acending order!*/
    public int shortest(String word1, String word2) {
        List<Integer> l1 =  map.get(word1);
        List<Integer> l2 =  map.get(word2);
        int distance = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while(i<l1.size()&&j<l2.size()){
            distance = Math.min(distance, Math.abs(l1.get(i)-l2.get(j)));
            if(l1.get(i)<l2.get(j)){
                i++;
            }else{
                j++;
            }
        }
        return distance;
    }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");
