/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* removeNthFromEnd(struct ListNode* head, int n) {
/*Two pointer*/
    if(!head || !n) return head;
    struct ListNode* dummy = (struct ListNode*)malloc(sizeof(struct ListNode));
    dummy->next = head;
    struct ListNode* fast = dummy;
    struct ListNode* slow = dummy;
    struct ListNode* tmp = NULL;
    while(fast && fast->next){
        while(n && fast){
            fast = fast->next;
            n--;
        }
        if(fast && fast->next){
            fast = fast->next;
            slow = slow->next;
        }
    }
    if(slow->next) tmp = slow->next->next;
    slow->next = tmp;
    return dummy->next;
}


/*Since the question gives that n is valid, not too many checks have to be put in place. Otherwise, this would be necessary.*/

public ListNode removeNthFromEnd(ListNode head, int n) {
    
    ListNode start = new ListNode(0);
    ListNode slow = start, fast = start;
    slow.next = head;
    
    //Move fast in front so that the gap between slow and fast becomes n
    for(int i=1; i<=n+1; i++)   {
        fast = fast.next;
    }
    //Move fast to the end, maintaining the gap
    while(fast != null) {
        slow = slow.next;
        fast = fast.next;
    }
    //Skip the desired node
    slow.next = slow.next.next;
    return start.next;
}


/*2nd*/
struct ListNode* removeNthFromEnd(struct ListNode* head, int n) {
/*Two pointer*/
    if(!head || !n) return head;
    struct ListNode* dummy = (struct ListNode*)malloc(sizeof(struct ListNode));
    dummy->next = head;
    struct ListNode* fast = dummy;
    struct ListNode* slow = dummy;
    while((n+1)){
        fast = fast->next;
        n--;
    }
    while(fast){
            fast = fast->next;
            slow = slow->next;
    }
    slow->next = slow->next->next;;
    return dummy->next;
}
