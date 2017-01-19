/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode *getIntersectionNode(struct ListNode *headA, struct ListNode *headB) {
    if(!headA || !headB) return NULL;
    struct ListNode* A = headA;
    struct ListNode* B = headB;
    int flag = 0;
    
    while (A && B && A!=B){
         A = A->next;
         B = B->next;
         if(A==B) return A;   // make sure no loop !SMART!
        
         if(!A ) {
             A= headB;
         }
         if(!B ) {
             B= headA;
         }
    }
    
    return A;
}
