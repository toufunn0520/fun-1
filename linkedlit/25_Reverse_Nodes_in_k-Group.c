/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
 /*每K个节点进行一次逆序，不足K个节点不进行逆序*/
 /*simple version to revert from startprev->next to end*/
struct ListNode* reverse(struct ListNode* startprev, struct ListNode *end, int k){
    struct ListNode * prev = NULL;
    struct ListNode * cur = startprev->next;
    int i = 0;
    
    while(i<k){
        i++;
        struct ListNode* tmp = cur->next;
        cur->next = prev;
        prev = cur;
        cur = tmp;
    }
   // printf("New head os %d\n", prev->val);
    return prev;
}

struct ListNode* reverseKGroup(struct ListNode* head, int k) {
    if(!head || !k || k==1) return head;
    struct ListNode * res = (struct ListNode*)malloc(sizeof(struct ListNode));
    res->next = head;
    struct ListNode * cur = head;
    struct ListNode * startprev = res;
    // every k element start to reverse
    int count =1;
    while(cur){
        if(count==k) {
            count = 1;
            struct ListNode * tmp = startprev->next;
            struct ListNode * updatednext = cur->next; 
            startprev->next = reverse(startprev, cur,k);
            
            //printf("OK2\n");
            tmp->next = updatednext;
            cur = tmp;
            startprev=cur;
           // printf("Reverse end=%d start=%d\n", cur->val);
        }else{
            count ++;
        }
        cur = cur->next;
    }
    return res->next;
}
