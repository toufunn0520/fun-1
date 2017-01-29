struct ListNode* deleteDuplicates(struct ListNode* head) {
    if(!head) return head;
    struct ListNode* dummy = malloc(sizeof(struct ListNode));
    dummy->next = head;
    struct ListNode* cur = dummy;
    
    while(cur && cur->next && cur->next->next){
        int val = cur->next->val;
        struct ListNode* next  = cur->next->next;
        if(val == next->val){
            while(next && next->val == val){
                next = next ->next;
            }
            cur->next = next;
        } else {
            cur = cur->next;
        }
    }
    return dummy->next;
}
