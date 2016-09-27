package category.graph.directed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CycleDetector {

    public static void main(String[] args) {
        CycleDetector c = new CycleDetector(10);
        c.addEdge(0, 9);
        c.addEdge(9, 1);

        System.out.println(c.isCyclic());
    }

    private List<List<Integer>> adjacents;

    private int V;

    public CycleDetector(int V) {
        if (V <= 0) {
            throw new IllegalArgumentException("V cannot be less than 0");
        }

        this.V = V;

        adjacents = new ArrayList<List<Integer>>();
        for (int i = 0; i < V; i++) {
            List<Integer> adjacent = new ArrayList<Integer>();
            adjacents.add(adjacent);
        }
    }

    public void addEdge(int v, int w) {
        if (v < 0 || w < 0) {
            throw new IllegalArgumentException("v or w is not valid");
        }

        this.adjacents.get(v).add(w);
    }

    public boolean isCyclic() {
        Set<Integer> visited = new HashSet<Integer>();

        for (int i = 0; i < V; i++) {
            if (isCyclicUtil(i, visited, new HashSet<Integer>())) {
                return true;
            }
        }

        return false;
    }

    private boolean isCyclicUtil(int current, Set<Integer> visited, Set<Integer> currentPath) {
        if (!currentPath.add(current)) {
            return true;
        }

        if (!visited.add(current)) {
            return false;
        }

        for (int adjacent : adjacents.get(current)) {
            if (isCyclicUtil(adjacent, visited, currentPath)) {
                return true;
            }
        }

        return false;
    }
}
