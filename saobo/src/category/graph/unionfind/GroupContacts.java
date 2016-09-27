package category.graph.unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Facebook onsite: 有这么一个class Contact，里面有一个string的name，和一个vector 装着email address，是这个Contact有的address，用一个list装着是因为一个人有可
 * 能有多个email，现在给你vector，比如 { { "John", {"john@gmail.com"} }, { "Mary", {"mary@gmail.com"} }, { "John",
 * {"john@yahoo.com"} }, { "John", {"john@gmail.com", "john@yahoo.com", "john@hotmail.com"} }, { "Bob",
 * {"bob@gmail.com"} } } 现在我们知道如果email address相同的话，那么就说明是同一个人，现在要做的是根据这些email
 * address，把同一个contact给group起来，生成一个vector<vector<Contact>>
 * 
 * @author boyi
 */
public class GroupContacts {

    public static void main(String[] args) {

        GroupContacts groupContacts = new GroupContacts(5);

        String[] emails1 = { "best@snapchat.com", "good@google.com" };
        String[] emails2 = { "noGreenCard@amazon.com" };
        String[] emails3 = { "good@google.com", "good@facebook.com" };
        String[] emails4 = { "noGreenCard@amazon.com", "mayBeFired@microsoft.com" };
        String[] emails5 = { "good@linkedin.com", "best@airbnb.com", "best@snapchat.com" };

        Contact candidateAAA = groupContacts.new Contact("snapchater", emails1);
        Contact candidateBBB = groupContacts.new Contact("amazonian", emails2);
        Contact candidateAAAA = groupContacts.new Contact("googler", emails3);
        Contact candidateWithoutGC = groupContacts.new Contact("microsofter", emails4);
        Contact candidateAAAAA = groupContacts.new Contact("linkediner", emails5);

        Contact[] contacts = new Contact[5];
        contacts[0] = candidateAAA;
        contacts[1] = candidateBBB;
        contacts[2] = candidateAAAA;
        contacts[3] = candidateWithoutGC;
        contacts[4] = candidateAAAAA;

        List<List<Contact>> result = groupContacts.groupContacts(contacts);

        for (List<Contact> contactList : result) {
            for (Contact contact : contactList) {
                System.out.println(contact.name);
            }
            System.out.println();
        }

    }

    private int[] father;

    private int[] ranks;

    public GroupContacts(int numberOfContacts) {
        father = new int[numberOfContacts];
        ranks = new int[numberOfContacts];

        for (int i = 0; i < numberOfContacts; i++) {
            father[i] = i;
            ranks[i] = 0;
        }
    }

    private int findRoot(int value) {
        if (value == father[value])
            return value;

        return father[value] = findRoot(father[value]);
    }

    private void union(int x, int y) {
        x = findRoot(x);
        y = findRoot(y);
        if (x == y)
            return;

        if (ranks[x] < ranks[y]) {
            father[x] = y;
        } else {
            father[y] = x;
            if (ranks[x] == ranks[y]) {
                ranks[x]++;
            }
        }
    }

    public List<List<Contact>> groupContacts(Contact[] contacts) {
        Map<String, List<Integer>> emailToIndex = new HashMap<String, List<Integer>>();

        int n = contacts.length;
        for (int i = 0; i < n; i++) {
            for (String email : contacts[i].emails) {
                if (emailToIndex.containsKey(email)) {
                    emailToIndex.get(email).add(i);
                } else {
                    List<Integer> indexes = new ArrayList<Integer>();
                    indexes.add(i);
                    emailToIndex.put(email, indexes);
                }
            }
        }

        for (Map.Entry<String, List<Integer>> entry : emailToIndex.entrySet()) {
            for (int i = 0; i < entry.getValue().size() - 1; i++) {
                union(entry.getValue().get(i), entry.getValue().get(i + 1));
            }
        }

        Map<Integer, List<Integer>> groups = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n; i++) {
            int rootIndex = findRoot(i);
            if (groups.containsKey(rootIndex)) {
                groups.get(rootIndex).add(i);
            } else {
                List<Integer> indexes = new LinkedList<Integer>();
                indexes.add(i);
                groups.put(rootIndex, indexes);
            }
        }

        List<List<Contact>> groupedContacts = new LinkedList<List<Contact>>();
        for (Map.Entry<Integer, List<Integer>> entry : groups.entrySet()) {
            List<Contact> contactsWithinGroup = new LinkedList<Contact>();
            for (Integer contactIndex : entry.getValue()) {
                contactsWithinGroup.add(contacts[contactIndex]);
            }
            groupedContacts.add(contactsWithinGroup);
        }

        return groupedContacts;
    }

    public class Contact {

        String name;

        String[] emails;

        Contact(String name, String[] emails) {
            this.name = name;
            this.emails = emails;
        }
    }
}
