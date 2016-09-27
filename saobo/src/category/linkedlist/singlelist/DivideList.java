package category.linkedlist.singlelist;

import java.util.ArrayList;
import java.util.List;

import category.linkedlist.ListNode;

public class DivideList {

    /**
     * [Snap*chat onsite] divide linkedlist by odd and even.
     * 
     * @param head
     * @return
     */
    public static List<ListNode> divideByOddAndEven(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummyOdd = new ListNode(-1);
        ListNode dummyEven = new ListNode(-2);

        ListNode oddCurrent = dummyOdd;
        ListNode evenCurrent = dummyEven;

        int count = 0;
        while (head != null) {
            if (count % 2 == 1) {
                evenCurrent.next = head;
                evenCurrent = evenCurrent.next;
            } else {
                oddCurrent.next = head;
                oddCurrent = oddCurrent.next;
            }
            head = head.next;
            count++;
        }

        evenCurrent.next = null;
        oddCurrent.next = null;

        List<ListNode> result = new ArrayList<ListNode>();
        result.add(dummyOdd.next);
        result.add(dummyEven.next);

        return result;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        List<ListNode> result = divideByOddAndEven(head);
        result.get(0).print();
        result.get(1).print();

    }
}
