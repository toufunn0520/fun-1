/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
 
struct ListNode* reverse(struct ListNode* head){
    if(!head) return head;
    struct ListNode* prev = NULL;
    struct ListNode* cur = head;
    while(cur){
        struct ListNode* next= cur->next;
        cur->next = prev;
        prev = cur;
        cur = next;
    }
    return prev;
}

void reorderList(struct ListNode* head) {
    if(!head) return head;
    struct ListNode* fast = head->next;
    struct ListNode* slow = head;
    struct ListNode* tmp ;

    /*find the mid*/
    while( fast && fast->next ){
        fast = fast->next->next;
        slow = slow ->next;
    }
    /*reverse the list start from mid*/
    tmp = slow;
    fast = head;
    slow = reverse(slow->next);
    // Important to seperate two list . Otherwise there will be loop
    tmp ->next = NULL; 
    
    /*merge HAVE to use cur->next to update the list*/
    struct ListNode* dummy = malloc(sizeof(struct ListNode));
    struct ListNode* cur = dummy;
    dummy->next = NULL;
    int i = 0;
    
    while( fast || slow ){  //careful fast is either equal or 1more than slow
        if( (i%2)==0 ){
            cur->next = fast;
            fast = fast->next;
        } else {
            cur->next = slow;
            slow = slow->next;
        }
        cur = cur->next;
        i++;
    }

    return dummy->next;
}
