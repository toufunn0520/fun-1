package category.linkedlist.singlelist;

public class CopyListWithRandomPointer {

    /**
     * [Leetcode 138] https://leetcode.com/problems/copy-list-with-random-pointer/
     *
     * <pre>
     * A linked list is given such that each node contains an additional random pointer which could point to any node in
     * the list or null. Return a deep copy of the list.
     * </pre>
     *
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        RandomListNode current = head;

        while (current != null) {
            RandomListNode next = current.next;
            current.next = new RandomListNode(current.label);
            current.next.next = next;
            current = next;
        }

        current = head;
        RandomListNode head2 = head.next;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }

            RandomListNode current2 = current.next;
            current.next = current.next.next;

            if (current.next != null) {
                current2.next = current.next.next;
            }
            current = current.next;
        }

        current = head;
        while (current != null) {
            RandomListNode current2 = current.next;
            current.next = current.next.next;

            if (current.next != null) {
                current2.next = current.next.next;
            }
            current = current.next;
        }
        return head2;
    }
}

class RandomListNode {

    int label;

    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }
}
