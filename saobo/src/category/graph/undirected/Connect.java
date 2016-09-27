package category.graph.undirected;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import category.graph.directed.DirectedGraphNode;

public class Connect {

    public static void main(String[] args) {
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);

        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        UndirectedGraphNode node3 = new UndirectedGraphNode(3);
        UndirectedGraphNode node4 = new UndirectedGraphNode(4);
        UndirectedGraphNode node5 = new UndirectedGraphNode(5);

    }

    /**
     * Find the number connected component in the undirected graph. Each node in the graph contains a label and a list
     * of its neighbors. (a connected component (or just component) of an undirected graph is a subgraph in which any
     * two vertices are connected to each other by paths, and which is connected to no additional vertices in the
     * supergraph.)
     *
     * @param nodes
     * @return
     */
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
        Queue<UndirectedGraphNode> processingQueue = new LinkedList<UndirectedGraphNode>();

        if (nodes == null) {
            return result;
        }

        for (UndirectedGraphNode node : nodes) {
            if (!visited.contains(node)) {
                List<Integer> currentSet = new ArrayList<Integer>();
                processingQueue.offer(node);
                visited.add(node);

                while (!processingQueue.isEmpty()) {
                    UndirectedGraphNode current = processingQueue.poll();
                    currentSet.add(current.label);

                    for (UndirectedGraphNode neighbor : current.neighbors) {
                        if (!visited.contains(neighbor)) {
                            processingQueue.offer(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }

                Collections.sort(currentSet);
                result.add(currentSet);
            }
        }
        return result;
    }

    /**
     * Find the number Weak Connected Component in the directed graph. Each node in the graph contains a label and a
     * list of its neighbors. (a connected set of a directed graph is a subgraph in which any two vertices are connected
     * by direct edge path.)
     *
     * @param nodes
     * @return
     */
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<DirectedGraphNode> processingQueue = new LinkedList<DirectedGraphNode>();
        Map<DirectedGraphNode, Integer> visited = new HashMap<DirectedGraphNode, Integer>();

        if (nodes == null) {
            return result;
        }

        for (DirectedGraphNode node : nodes) {
            processingQueue.offer(node);

            while (!processingQueue.isEmpty()) {
                DirectedGraphNode current = processingQueue.poll();

                for (DirectedGraphNode neighbor : current.neighbors) {

                }
            }
        }

        return result;
    }
}
