/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* partition(struct ListNode* head, int x) {
    if (!head) return head;
    struct ListNode* Leftdummy = (struct ListNode*)malloc(sizeof(struct ListNode));
	  struct ListNode* Rightdummy = (struct ListNode*)malloc(sizeof(struct ListNode));
    struct ListNode *left = Leftdummy;
    struct ListNode *right = Rightdummy;
    struct ListNode *tmp = head;
    while(tmp){
        if (tmp->val<x){
            left-> next = tmp;
            left = left->next;
        }else {
            right->next = tmp;
            right = right->next;
        }
        tmp= tmp->next;
    }
    right->next = NULL;
    left->next = Rightdummy->next;
    return Leftdummy->next;
}


/*2nd*/
struct ListNode* partition(struct ListNode* head, int x) {
    if(!head) return head;
    struct ListNode* dummy = malloc(sizeof(struct ListNode));
    dummy->next = head;
    struct ListNode* cur = dummy;
    struct ListNode* larger = malloc(sizeof(struct ListNode));
    larger->next = NULL;
    struct ListNode* tmp = larger;
    
    while(cur && cur->next){
        if(cur->next->val >=x){
            tmp->next = cur->next;
            cur->next = cur->next->next;  // like been updated cur
            tmp = tmp->next;
        }else{
           cur = cur->next;
        }
    }
    tmp->next = NULL; // Import
    if(larger->next) cur->next = larger->next;
    return dummy->next;
}
