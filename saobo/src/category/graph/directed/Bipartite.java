package category.graph.directed;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Bipartite {

    static class Graph {

        Set<Node> nodes;

        public Graph() {
            nodes = new HashSet<Node>();
        }

        public void addEdge(Node a, Node b) {
            a.neighbors.add(b);
            b.neighbors.add(a);
            nodes.add(a);
            nodes.add(b);
        }

        public boolean isBipartite() {
            if (nodes.isEmpty()) {
                return false;
            }

            Map<Node, Integer> node2Color = new HashMap<Node, Integer>();

            Queue<Node> processingQueue = new LinkedList<Node>();
            Node root = nodes.iterator().next();
            node2Color.put(root, 1);
            processingQueue.offer(root);

            while (!processingQueue.isEmpty()) {
                int currentSize = processingQueue.size();

                for (int i = 0; i < currentSize; i++) {
                    Node current = processingQueue.poll();
                    int currentColor = node2Color.get(current);

                    for (Node neighbor : current.neighbors) {
                        if (node2Color.containsKey(neighbor) && node2Color.get(neighbor) == currentColor) {
                            return false;
                        } else {
                            node2Color.put(neighbor, 3 - currentColor);
                        }
                    }
                }
            }

            return true;
        }
    }

    static class Node {

        Set<Node> neighbors;

        public Node() {
            neighbors = new HashSet<Node>();
        }
    }

    public static void main(String[] args) {
        Bipartite.Graph graph = new Bipartite.Graph();
        graph.addEdge(new Node(), new Node());

    }

}
