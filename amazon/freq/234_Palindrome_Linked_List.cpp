/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
 
struct ListNode* reverse(struct ListNode* head){
    if(!head) return head;
    struct ListNode* cur = head;
    struct ListNode* prev = NULL;
    while(cur){
        struct ListNode* next = cur->next;
        cur->next = prev;
        prev = cur;
        cur = next;
    }
    return prev;
}

bool isPalindrome(struct ListNode* head) {  
    if(!head) return true;
    struct ListNode* fast = head->next;
    struct ListNode* slow = head;
    while (fast && fast->next) {
        fast = fast->next->next;
        slow = slow->next;
    }
    struct ListNode* right = reverse(slow->next);
    while (head && right) {
        if (head -> val != right->val) return false;
        head = head -> next;
        right = right ->next;
    }
    return true;
}
