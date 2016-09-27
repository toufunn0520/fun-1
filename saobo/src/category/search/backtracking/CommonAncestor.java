package category.search.backtracking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommonAncestor {

    /**
     * [snap*chat] 给一个Employee类，有一个String name和一个List<Employee>
     * directReport来表示一个公司的组织结构，然后给定一个公司的ceo和两个员工的名字，找到这两个员工的第一个common manager，给的两个员工一定存在。
     * 
     * @return
     */
    public String getCommonAncestor(Employee ceo, Employee employee1, Employee employee2) {
        if (ceo == null || employee1 == null || employee2 == null) {
            return null;
        }

        List<Employee> orgChain1 = getOrgChain(ceo, employee1, new ArrayList<Employee>());
        List<Employee> orgChain2 = getOrgChain(ceo, employee2, new ArrayList<Employee>());

        Iterator<Employee> iterator1 = orgChain1.iterator();
        Iterator<Employee> iterator2 = orgChain2.iterator();

        String lastCommonBoss = ceo.name;
        while (iterator1.hasNext() && iterator2.hasNext()) {
            String name1 = iterator1.next().name;
            String name2 = iterator2.next().name;

            if (name1.equals(name2)) {
                lastCommonBoss = name1;
            } else {
                break;
            }
        }

        return lastCommonBoss;
    }

    private List<Employee> getOrgChain(Employee boss, Employee employee, List<Employee> path) {
        if (boss.equals(employee)) {
            path.add(employee);
            return path;
        }

        for (Employee current : boss.directReports) {
            path.add(current);
            if (getOrgChain(current, employee, path) != null) {
                return path;
            }
            path.remove(current);
        }

        return null;
    }
}

class Employee {

    List<Employee> directReports;

    String name;
}
