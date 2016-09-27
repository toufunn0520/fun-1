package category.graph.undirected;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {

    /**
     * [Leetcode 133] https://leetcode.com/problems/clone-graph/
     * 
     * <pre>
     * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
     * </pre>
     * 
     * @param node
     * @return
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        Map<Integer, UndirectedGraphNode> label2Node = new HashMap<Integer, UndirectedGraphNode>();
        queue.offer(node);
        while (!queue.isEmpty()) { // BFS
            UndirectedGraphNode cur = queue.poll();
            if (!label2Node.containsKey(cur.label))
                label2Node.put(cur.label, new UndirectedGraphNode(cur.label)); // put in map to set visited
            if (cur.neighbors != null) {
                for (UndirectedGraphNode n : cur.neighbors) {
                    if (!label2Node.containsKey(n.label)) {
                        queue.offer(n);
                        label2Node.put(n.label, new UndirectedGraphNode(n.label));
                    }
                    // add to neighbors
                    label2Node.get(cur.label).neighbors.add(label2Node.get(n.label));
                }
            }
        }
        return label2Node.get(node.label);
    }
}
