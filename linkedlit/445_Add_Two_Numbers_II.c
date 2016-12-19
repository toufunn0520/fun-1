/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
 // reverse the list
struct ListNode* Reverse(struct ListNode* l1){
    if(!l1) return l1;
    
    struct ListNode* prev = NULL;
    struct ListNode* cur = l1;
    while(cur){
        struct ListNode* next = cur->next;
        cur->next = prev;
        prev = cur;
        cur = next;
    }
    return prev;
    
}

struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2) {
    if(!l1) return l2;
    if(!l2) return l1;
    
    struct ListNode* newl1= Reverse(l1);
    struct ListNode* newl2= Reverse(l2);    
    
    struct ListNode* head = (struct ListNode*) malloc(sizeof(struct ListNode*));
    struct ListNode* iter = head;
    int sum = 0;
    int carry =0;
    
    while(newl1 || newl2){
        if(newl1) {
            sum += newl1->val;
            newl1 = newl1->next;
        }
        if(newl2) {
            sum += newl2->val;
            newl2 = newl2->next;
        }
        sum += carry;
        carry = sum / 10;
        sum = sum % 10;
        //  printf("Sum =%d Carry =%d\n", sum, carry);
        struct ListNode* tmp = (struct ListNode*)malloc(sizeof(struct ListNode));
        iter->next = tmp;
        tmp ->val = sum;
        tmp ->next = NULL;
        iter = tmp;
        sum = 0;
    }
    
    if(carry){
        struct ListNode* tmp = (struct ListNode*)malloc(sizeof(struct ListNode));
        iter->next = tmp;
        tmp ->val = carry;
        tmp ->next = NULL;
        iter = tmp;
    }

    return Reverse(head->next);
    
}
