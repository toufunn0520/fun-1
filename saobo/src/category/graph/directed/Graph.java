package category.graph.directed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

public class Graph {

    /**
     * All directed adjacents. key is the prerequisite of all the characters in the list of value.
     */
    private Map<Character, List<Character>> adjacents;

    public Graph() {
        adjacents = new HashMap<Character, List<Character>>();
    }

    /**
     * Add directed edge with two chars.
     *
     * @param fromNode
     * @param toNode
     */
    public void addEdge(char fromNode, char toNode) {
        List<Character> fromAdjacent = null;
        if (adjacents.containsKey(fromNode)) {
            fromAdjacent = adjacents.get(fromNode);
        } else {
            fromAdjacent = new ArrayList<Character>();
            adjacents.put(fromNode, fromAdjacent);
        }

        if (!adjacents.containsKey(toNode)) {
            List<Character> toAdjacent = new ArrayList<Character>();
            adjacents.put(toNode, toAdjacent);
        }

        fromAdjacent.add(toNode);
    }

    @Test
    public void testWhenBlankCharInserted() {
        Graph graph = new Graph();
        graph.addEdge('a', 'c');
        graph.addEdge(' ', 'a');
        List<Character> expectedSortedList = Arrays.asList(' ', 'a', 'c');

        Assert.assertEquals(expectedSortedList, graph.topologicalSort());
    }

    @Test
    public void testWhenMultipleEdgesInserted() {
        Graph graph = new Graph();
        graph.addEdge('a', 'c');
        graph.addEdge('t', 'b');
        graph.addEdge('o', 't');
        graph.addEdge('c', 'o');
        graph.addEdge('b', 'z');
        graph.addEdge('a', 'z');
        graph.addEdge('b', 'z');
        graph.addEdge('c', 't');
        List<Character> expectedSortedList = Arrays.asList('a', 'c', 'o', 't', 'b', 'z');

        Assert.assertEquals(expectedSortedList, graph.topologicalSort());
    }

    @Test
    public void testWhenMultipleEdgesInsertedWithMoreThanOneOptions() {
        Graph graph = new Graph();
        graph.addEdge('a', 'c');
        graph.addEdge('a', 'b');
        graph.addEdge('o', 't');
        graph.addEdge('v', 'o');
        graph.addEdge('c', 'z');

        List<Character> result = graph.topologicalSort();
        Map<Character, Integer> charToId = new HashMap<Character, Integer>();
        for (int i = 0; i < result.size(); i++) {
            charToId.put(result.get(i), i);
        }

        Assert.assertTrue(charToId.get('a') < charToId.get('c'));
        Assert.assertTrue(charToId.get('a') < charToId.get('b'));
        Assert.assertTrue(charToId.get('o') < charToId.get('t'));
        Assert.assertTrue(charToId.get('v') < charToId.get('o'));
        Assert.assertTrue(charToId.get('c') < charToId.get('z'));
    }

    @Test
    public void testWhenNoEdgeInGraph() {
        Assert.assertEquals(new ArrayList<Character>(), new Graph().topologicalSort());
    }

    @Test
    public void testWhenOneEdgeInserted() {
        Graph graph = new Graph();
        graph.addEdge('a', 'c');
        List<Character> expectedSortedList = Arrays.asList('a', 'c');

        Assert.assertEquals(expectedSortedList, graph.topologicalSort());
    }

    /**
     * @return one possible sort.
     */
    public List<Character> topologicalSort() {
        List<Character> sortedList = new ArrayList<Character>();

        Stack<Character> stack = new Stack<Character>();
        Set<Character> visited = new HashSet<Character>();

        for (Character adjacent : adjacents.keySet()) {
            if (visited.add(adjacent)) {
                topologicalSortUtil(adjacent, visited, stack);
            }
        }

        while (!stack.empty()) {
            sortedList.add(stack.pop());
        }

        return sortedList;
    }

    private void topologicalSortUtil(char currentChar, Set<Character> visited, Stack<Character> stack) {

        for (char adjacentChar : adjacents.get(currentChar)) {
            if (visited.add(adjacentChar)) {
                topologicalSortUtil(adjacentChar, visited, stack);
            }
        }

        stack.push(currentChar);
    }
}
