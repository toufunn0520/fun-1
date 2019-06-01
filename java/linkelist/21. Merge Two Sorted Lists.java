/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l1node  = l1;
        ListNode l2node = l2;
        
        ListNode start = new ListNode(0);
        ListNode tmp = start;
        
        while (l1node !=null && l2node !=null) {
            if(l1node.val <= l2node.val) {
                tmp.next = l1node;
                l1node = l1node.next;
            } else {
                tmp.next = l2node;
                l2node = l2node.next;
            }
            tmp = tmp.next;
        }
        
        if (l1node != null) {
            tmp.next = l1node;
        }
        if (l2node != null) {
            tmp.next = l2node;
        }
        
        return start.next;
    }
}
