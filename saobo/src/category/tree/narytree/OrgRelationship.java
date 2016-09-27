package category.tree.narytree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * [snapchat]
 *
 * <pre>
 * 输入为一个文件，每一行格式：下级名字，上司名字。
 * 输出：
 * >A
 * >>A的下级B的名字
 * >>>B的下级C的名字
 * >>A的下级D的名字
 * </pre>
 *
 * @author boyi
 */
public class OrgRelationship {

    public static void main(String[] args) {
        List<String> relationships = Arrays.asList("a,b", "b,c", "a,d", "d,e", "x,z", "x,o");

        OrgRelationship o = new OrgRelationship();
        o.printOrg(relationships);
    }

    private Set<String> bosses;

    private Map<String, Set<String>> manager2Reports;

    public OrgRelationship() {
        manager2Reports = new HashMap<String, Set<String>>();
        bosses = new HashSet<String>();
    }

    private void dfs(String current, int layer) {
        printWithIndent(layer, current);
        if (manager2Reports.containsKey(current)) {
            for (String report : manager2Reports.get(current)) {
                dfs(report, layer + 1);
            }
        }
    }

    public void printOrg(List<String> relationships) {

        for (String relationship : relationships) {
            bosses.add(relationship.split(",")[0]);
        }

        for (String relationship : relationships) {
            String[] personStrings = relationship.split(",");
            String manager = personStrings[0];
            String report = personStrings[1];

            bosses.remove(report);

            if (manager2Reports.containsKey(manager)) {
                manager2Reports.get(manager).add(report);
            } else {
                Set<String> reports = new HashSet<String>();
                reports.add(report);
                manager2Reports.put(manager, reports);
            }
        }

        for (String currentName : bosses) {
            dfs(currentName, 1);
        }
    }

    private void printWithIndent(int indents, String string) {
        for (int i = 0; i < indents; i++) {
            System.out.print(">");
        }
        System.out.println(string);
    }
}
