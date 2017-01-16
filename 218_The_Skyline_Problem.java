public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
                List<int[]> result = new ArrayList<int[]>();  
                PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new Comparator<Integer>()
                {
                    @Override  
                    public int compare(Integer o1, Integer o2) {  
                       // TODO Auto-generated method stub  
                       return o2 - o1;  
                }  
                });
                //保存即将排序后的轮廓边缘点  
                List<int[]> sort = new ArrayList<int[]>();  
                for(int i =0; i<buildings.length; i++){
                    sort.add(new int[]{buildings[i][0], buildings[i][2]});
                    sort.add(new int[]{buildings[i][1], -buildings[i][2]});
                }
                Collections.sort(sort, new Comparator<int[]>(){
                     @Override  
                    public int compare(int[] o1, int[] o2) {  
                      if(o1[0]!=o2[0]){
                          return o1[0] - o2[0];
                      }else{
                          return o2[1] - o1[1];
                      }
                    }
                });
                
                //将所有轮廓边缘点排序
                int pre =0;
                int cur =0;
                for(int i =0; i<sort.size(); i++){
                    int[] building = sort.get(i);
                    if(building[1] >0){
                        maxHeap.add(building[1]);
                    }else{
                        maxHeap.remove(-building[1]);
                    }
                    
                    //update cur:
                    cur = (maxHeap.peek() == null) ? 0:maxHeap.peek(); 
                    if(cur != pre){
                        result.add(new int[]{building[0], cur});  
                        pre=cur;
                    }
                }
                
                return result;
                
    }
}
