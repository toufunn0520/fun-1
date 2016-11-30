/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
 
struct ListNode* swapPairs(struct ListNode* head) {
    if(!head) return head;
    struct ListNode* dummy = (struct ListNode*)malloc(sizeof(struct ListNode));
    dummy->next = head;
    struct ListNode *cur = head;
    struct ListNode * prev = dummy;

    while(cur && cur->next){
        struct ListNode * next = cur->next->next;
        struct ListNode * tmp = cur->next;
        //swap
        cur->next = next;
        prev->next = tmp;
        tmp->next = cur;
        
        prev = cur;
        cur = cur->next;
    }
    return dummy->next;
}
