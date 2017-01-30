/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* rotateRight(struct ListNode* head, int k) {
    //corner case list empty/k=0;
    if (!head) return head;
    
    struct ListNode* dummy = (struct ListNode*)malloc(sizeof(struct ListNode));
    struct ListNode* fast = head;
    struct ListNode* slow = head;
    int len = 0;
    //figure out the len of the list
    while(fast) {
        len++;
        fast = fast->next;
    }
    // mod len in case k is bigger than len    
    k = k%len;
    if(!k) return head; // trick check k here!!
    fast = head;
    for(int i =0; i<k;i++){
        fast = fast->next;
    }
    //!!!find the newstart prev
    while(fast->next){
        slow = slow->next;
        fast = fast->next;
    }
    //final adjustment
    dummy->next =slow->next;
    fast->next = head;
    slow->next = NULL;
    return dummy->next;
}

/*2nd similar approach*/
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* rotateRight(struct ListNode* head, int k) {
    if(!head) return head;
    int len = 0;
    struct ListNode* cur = head;
    while(cur){
        cur = cur->next;
        len ++;
    }
    struct ListNode* fast = head;
    cur = head;
    k = k%len;
    if(!k) return head;
    
    while(k){
        fast = fast->next;
        k--;
    }
    while(fast && fast->next){
        cur = cur->next;
        fast = fast->next;
    }
    fast->next = head;
    head = cur->next;
    cur->next = NULL;
    return head;
}
