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
