package category.tree.narytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParentChildren {

    public static void main(String[] args) {
        List<Relation> relations = new ArrayList<Relation>();
        relations.add(new Relation("A", "B1"));
        relations.add(new Relation("A", "B2"));
        relations.add(new Relation("A", "B3"));
        relations.add(new Relation("B2", "C1"));
        relations.add(new Relation("B2", "C2"));
        relations.add(new Relation("B3", "D1"));
        relations.add(new Relation("B3", "D2"));
        relations.add(new Relation("Z", "BF"));
        relations.add(new Relation("BF", "OP"));

        new ParentChildren().printLayeredRelation(relations.iterator());

    }

    private void dfsPrint(String root, int layerIndex, Map<String, Set<String>> relationMap, Set<String> visited) {
        if (visited.contains(root)) {
            return;
        }

        visited.add(root);
        printWithSpace(layerIndex, root);

        if (relationMap.containsKey(root) && !relationMap.get(root).isEmpty()) {
            for (String child : relationMap.get(root)) {
                dfsPrint(child, layerIndex + 1, relationMap, visited);
            }
        }
    }

    /**
     * [Google phone] Given an iterator which iterates a mapping of parent to child string. One parent per child, many
     * children per parent. Print layered structures.
     *
     * @param iterator
     */
    public void printLayeredRelation(Iterator<Relation> iterator) {
        if (iterator == null) {
            return;
        }

        Map<String, Set<String>> parent2children = new HashMap<String, Set<String>>();
        Map<String, String> child2parent = new HashMap<String, String>();

        while (iterator.hasNext()) {
            Relation current = iterator.next();
            child2parent.put(current.child, current.parent);

            if (parent2children.containsKey(current.parent)) {
                parent2children.get(current.parent).add(current.child);
            } else {
                Set<String> children = new HashSet<String>();
                children.add(current.child);

                parent2children.put(current.parent, children);
            }
        }

        Set<String> visited = new HashSet<String>();
        for (String parent : parent2children.keySet()) {
            if (visited.contains(parent)) {
                continue;
            }

            while (child2parent.containsKey(parent)) {
                parent = child2parent.get(parent);
            }

            dfsPrint(parent, 0, parent2children, visited);
        }

    }

    private void printWithSpace(int countOfSpaces, String s) {
        for (int i = 0; i < countOfSpaces; i++) {
            System.out.print(" ");
        }
        System.out.println(s);
    }
}
