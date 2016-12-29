/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    Map<Integer, UndirectedGraphNode>map = new HashMap<Integer, UndirectedGraphNode>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
          return clone(node);
    }
    private UndirectedGraphNode clone(UndirectedGraphNode node){
        if(node == null) return node;
        
        if (map.containsKey(node.label)) return map.get(node.label);  // not to redo visted nodes
        
        UndirectedGraphNode dup = new UndirectedGraphNode(node.label);
        map.put(node.label, dup);
        for(UndirectedGraphNode n: node.neighbors){
            dup.neighbors.add(clone(n)); // key clone(n)
        }
        return dup;
    }
}

/*Solution 2 BFS*/
public class Solution {
     public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
         if(node == null) return null;
         
         Map<Integer, UndirectedGraphNode>map = new HashMap<Integer, UndirectedGraphNode>();
         LinkedList<UndirectedGraphNode>queue = new LinkedList<>();
         
         UndirectedGraphNode new_node = new UndirectedGraphNode(node.label);
         queue.add(node);
         map.put(node.label, new_node);
         
         //BFS
         while(!queue.isEmpty()){
             UndirectedGraphNode cur = queue.pop();
             for(UndirectedGraphNode n: cur.neighbors){
                    if(!map.containsKey(n.label)){
                        // 3 things add key into map, add original n into queue, add n_new into cur->clone.neighbor list
                        UndirectedGraphNode new_n = new UndirectedGraphNode(n.label);
                        map.put(n.label,new_n);
                        queue.add(n);
                    }
              map.get(cur.label).neighbors.add(map.get(n.label));   // to itself need to satisefied each edges. There is no duplicate anyway

             }
             
         }
         return new_node;
         
     }
}
