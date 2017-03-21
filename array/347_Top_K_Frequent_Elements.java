/*Bucket sort*/
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequency = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        
        for(int i=0; i<nums.length; i++){
            //frequencyMap.put(nums[i], frequencyMap.getOrDefault(nums[i], 0) + 1);
            if(frequency.containsKey(nums[i])){
                frequency.put(nums[i], frequency.get(nums[i])+1);
            }else{
                frequency.put(nums[i], 1);
            }
        }
        
        for(int key : frequency.keySet()){
            int f = frequency.get(key);
            if(bucket[f] == null){
                bucket[f] = new ArrayList<Integer>();
            }
            bucket[f].add(key);
        }
        
        for(int i = nums.length; (i>0)&&(res.size()<k);i--){
            if(bucket[i]!=null){
                res.addAll(bucket[i]);
            }
        }
        return res;
    }
}
