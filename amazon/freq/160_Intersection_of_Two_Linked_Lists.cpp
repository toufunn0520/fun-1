 */
struct ListNode *getIntersectionNode(struct ListNode *headA, struct ListNode *headB) {
    if(!headA || !headB) return NULL;
    struct ListNode* A = headA;
    struct ListNode* B = headB;

    while (A && B && A!=B){
         A = A->next;
         B = B->next;
         if(A==B) return A;   // key if NULL make sure no loop !SMART! NULL
        
         if(!A ) {
             A= headB;
         }
         if(!B ) {
             B= headA;
         }
    }
    
    return A;
}
