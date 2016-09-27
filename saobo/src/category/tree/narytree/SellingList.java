package category.tree.narytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Employee {

    String name;

    int numOfIndividualItems;

    int numOfTotalItems;

    List<Employee> reports;

    Employee(String name, int numOfSellingItems) {
        this.name = name;
        this.numOfIndividualItems = numOfSellingItems;
        this.reports = new ArrayList<Employee>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name + "[");
        for (Employee report : reports) {
            sb.append(report.name + " ");
        }
        sb.append("]");
        return sb.toString();
    }
}

public class SellingList {

    public static void main(String[] args) {
        String s1 = "Alice,,5";
        String s2 = "Bob,Alice,3";
        String s3 = "Carol,Bob,2";
        String s4 = "David,Bob,3";
        String s5 = "Eve,Alice,2";
        String s6 = "Ferris,Eve,1";

        List<String> list = Arrays.asList(s1, s2, s3, s4, s5, s6);
        printSellingList(list);
    }

    private static void printReports(List<Employee> reports, String prefix) {
        int i = 0;
        Employee current;
        for (i = 0; i < reports.size() - 1; i++) {
            current = reports.get(i);

            System.out.println(prefix + "-" + current.name + " " + current.numOfTotalItems);
            if (current.reports.size() != 0) {
                printReports(current.reports, prefix + "|");
            }
        }

        if (i == reports.size() - 1) {
            current = reports.get(i);
            System.out.println(prefix.substring(0, prefix.length() - 1) + "\\_" + current.name + " "
                    + current.numOfTotalItems);

            if (current.reports.size() != 0) {
                printReports(current.reports, prefix.substring(0, prefix.length() - 1) + "  ");
            }
        }
    }

    /**
     * [snap*chat]
     *
     * <pre>
     * Given inputs:
     *
     * Employee,Manager,ItemsSold
     * Alice,,5
     * Bob,Alice,3
     * Carol,Bob,2
     * David,Bob,3
     * Eve,Alice,2
     * Ferris,Eve,1
     *
     * print the structure like following:
     *
     * Alice 16
     * |-Bob 8
     * | |-Carol 2
     * | \_David 3
     * \_Eve 3
     *   \_Ferris 1
     * </pre>
     *
     * @param sellingList
     */
    public static void printSellingList(List<String> sellingList) {
        if (sellingList == null || sellingList.size() == 0) {
            return;
        }

        Set<Employee> ceos = new HashSet<Employee>();
        Map<String, Employee> name2Employee = new HashMap<String, Employee>();

        for (String str : sellingList) {
            String[] splitted = str.split(",");
            String employeeName = splitted[0];
            String bossName = splitted[1];
            int numOfItems = Integer.parseInt(splitted[2]);

            if (bossName.isEmpty()) {
                Employee ceo = new Employee(employeeName, numOfItems);
                ceos.add(ceo);
                name2Employee.put(employeeName, ceo);
            } else {
                if (!name2Employee.containsKey(bossName)) {
                    name2Employee.put(bossName, new Employee(bossName, 0));
                }

                if (!name2Employee.containsKey(employeeName)) {
                    name2Employee.put(employeeName, new Employee(employeeName, numOfItems));
                }

                Employee boss = name2Employee.get(bossName);
                Employee employee = name2Employee.get(employeeName);
                boss.reports.add(employee);
            }
        }

        for (Employee ceo : ceos) {
            updateTotalItems(ceo);
        }

        for (Employee ceo : ceos) {
            System.out.println(ceo.name + " " + ceo.numOfTotalItems);
            printReports(ceo.reports, "|");
        }

    }

    private static void updateTotalItems(Employee employee) {
        int total = employee.numOfIndividualItems;

        for (Employee report : employee.reports) {
            updateTotalItems(report);
            total += report.numOfTotalItems;
        }

        employee.numOfTotalItems = total;
    }
}
