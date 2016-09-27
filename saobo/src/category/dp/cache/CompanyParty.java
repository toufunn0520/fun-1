package category.dp.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyParty {

    /**
     * Professor Stewart is consulting for the president of a corporation that is planning a company party. The company
     * has a hierarchical structure; that is, the supervisor relation forms a tree rooted at the president. The
     * personnel office has ranked each employee with a conviviality rating, which is a real number. In order to make
     * the party fun for all attendees, the president does not want both an employee and his or her immediate supervisor
     * to attend. Professor Stewart is given the tree that describes the structure of the corporation, using the
     * left-child, right-sibling representation described in Section 10.4. Each node of the tree holds, in addition to
     * the pointers, the name of an employee and that employee’s conviviality ranking. Describe an algorithm to make up
     * a guest list that maximizes the sum of the conviviality ratings of the guests. Analyze the running time of your
     * algorithm.
     *
     * @return
     */
    public static int getMaxConviviality(Person ceo) {
        if (ceo == null) {
            return 0;
        }

        Map<Person, Integer> maxConvivialityUnderThePerson = new HashMap<Person, Integer>();

        return getMaxConviviality(ceo, maxConvivialityUnderThePerson);
    }

    private static int getMaxConviviality(Person person, Map<Person, Integer> maxConvivialityWithSelf) {
        if (maxConvivialityWithSelf.containsKey(person)) {
            return maxConvivialityWithSelf.get(person);
        }

        int conviviality = 0;
        if (person.conviviality > 0) {
            conviviality = person.conviviality;
            for (Person report : person.reports) {
                for (Person grandReport : report.reports) {
                    conviviality += getMaxConviviality(grandReport, maxConvivialityWithSelf);
                }
            }
        } else {
            for (Person report : person.reports) {
                conviviality += getMaxConviviality(report, maxConvivialityWithSelf);
            }
        }

        maxConvivialityWithSelf.put(person, conviviality);
        return conviviality;
    }

    public static void main(String[] args) {
        Person ceo = new Person(100);
        Person di1 = new Person(-3);
        Person di2 = new Person(20);

        Person ma1 = new Person(10);
        Person ma2 = new Person(100);

        ceo.reports = Arrays.asList(di1, di2);
        di1.reports = Arrays.asList(ma1);
        di2.reports = Arrays.asList(ma2);

        System.out.println(getMaxConviviality(ceo));
    }
}

class Person {

    int conviviality;

    Person manager;

    List<Person> reports;

    Person(int conviviality) {
        this.conviviality = conviviality;
        reports = new ArrayList<Person>();
    }
}
