package category.linkedlist;

public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public int getLength() {
        int count = 0;
        ListNode current = this;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    @Override
    public String toString() {
        if (next != null) {
            return val + "->" + next.toString();
        } else {
            return "" + val;
        }
    }

    public void print() {

        ListNode current = this;
        while (current != null) {
            System.out.print(current.val);
            current = current.next;
            System.out.print(" -> ");
        }
        System.out.println();
    }
}
