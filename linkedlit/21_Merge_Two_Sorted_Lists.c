int MAX(int p, int q){
     return p>q?p:q;
 }
struct ListNode* mergeTwoLists(struct ListNode* l1, struct ListNode* l2) {
    if(!l1) return l2;
    if(!l2) return l1;
    struct ListNode *head = (struct ListNode*)malloc(sizeof(struct ListNode));
    struct ListNode *tmp = head;
    while(l1 && l2){
        if(l1->val <= l2->val){
            tmp->next = l1;
            l1 = l1->next;
        }else{
            tmp ->next = l2;
            l2 = l2->next;
        }
        tmp = tmp->next;
    } 
    if(l1){
        tmp->next = l1;
    }
    if(l2){
        tmp->next = l2;
    }
    return head ->next;
}
