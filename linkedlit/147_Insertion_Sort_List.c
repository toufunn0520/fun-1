/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
 
 /*take node and put it into dummy list */
struct ListNode* insertionSortList(struct ListNode* head) {
    if(!head) return head;
    struct ListNode* dummy = (struct ListNode*)malloc(sizeof(struct ListNode));
    dummy->val= 0;
    dummy->next = NULL;
    while(head){

        // find insertion position of nodes
        struct ListNode* node = dummy;
        // shit node->next
        while(node->next && head->val>node->next->val ){
            node= node->next;
        }
        //insertion happened.
        struct ListNode* tmp = head->next;
        head->next = node->next;
        node->next = head;
        head = tmp;
       // printf("Insert %d\n", node->next->val);
    }
    return dummy->next;
}

/*2nd */
struct ListNode* insertionSortList(struct ListNode* head) {
    if(!head) return head;
    struct ListNode* dummy = (struct ListNode*)malloc(sizeof(struct ListNode));
    dummy->next = NULL;
    struct ListNode* cur  = head;
    struct ListNode* prev = dummy;
    while(cur){
        struct ListNode* next = cur->next;
        /*Find Insertion postion*/
        while(prev && prev->next && (cur->val >= prev->next->val)){
            prev = prev->next;
        }
        
        cur->next = prev->next;
        prev->next = cur;
        prev = dummy;
        cur = next;
    }
    return dummy->next;
}
