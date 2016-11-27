/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* reverse(struct ListNode* head){
    struct ListNode* prev = NULL;
    struct ListNode* next = NULL;
    struct ListNode* cur = head;
    while(cur){
        next = cur->next;
        cur->next = prev;
        prev = cur;
        cur = next;
    }
    return prev;
} 

struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2) {
    if(!l1) return l2;
    if(!l2) return l1;
    
    /*reverse the lists*/
    struct ListNode*newl1 = l1;
    struct ListNode*newl2 = l2;
    
    /*calculate*/
    struct ListNode* curl1 = newl1;
    struct ListNode* curl2 = newl2;
    struct ListNode* result = (struct ListNode*)malloc(sizeof(struct ListNode));
    struct ListNode* tmp = result;
    struct ListNode* prev = tmp;

    int carry = 0;
    
    while(curl1 && curl2){
        tmp->val = (curl1->val + curl2->val+ carry) %10;
        carry = (curl1->val + curl2->val+ carry) /10;
        tmp->next = (struct ListNode*)malloc(sizeof(struct ListNode));
        prev = tmp;
        tmp = tmp->next;
        curl1 = curl1->next;
        curl2 = curl2->next;
    }
    while(curl1){
        tmp->val= (carry + curl1->val)%10;
        carry = (carry + curl1->val)/10;
        tmp->next = (struct ListNode*)malloc(sizeof(struct ListNode));
        prev = tmp;
        tmp = tmp->next;
        curl1 = curl1->next;
    }
    
    while(curl2){
        tmp->val= (carry + curl2->val)%10;
        carry = (carry + curl2->val)/10;
        tmp->next = (struct ListNode*)malloc(sizeof(struct ListNode));
        prev = tmp;
        tmp = tmp->next;
        curl2 = curl2->next;
    }
    //KEY forget the last carry
    if(carry) {
         tmp->val=carry;
         tmp->next = (struct ListNode*)malloc(sizeof(struct ListNode));
         prev = tmp;
         tmp = tmp->next;
    }
    prev->next = NULL;
    
   // struct ListNode *haha = reverse(result);
    return result; 
    
}
