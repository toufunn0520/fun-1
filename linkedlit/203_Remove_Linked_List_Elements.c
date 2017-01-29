/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
 
struct ListNode* removeElements(struct ListNode* head, int val) {
    if (!head) return head;
    struct ListNode* dummy = (struct ListNode*)malloc(sizeof(struct ListNode));
    dummy->next = head;
    struct ListNode* cur = dummy;
    while( cur && (cur->next) ){
        if ( cur->next->val == val ){
            cur->next = cur->next->next;
            continue;  //Key we need thise what if we have 1->1 and val =1
        }
        cur = cur->next;
    }
    return dummy->next;
}
