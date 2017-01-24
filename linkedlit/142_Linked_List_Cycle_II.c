struct ListNode *detectCycle(struct ListNode *head) {
    if(!head) return head;
    /*Find cycle*/
    struct ListNode* fast = head;
    struct ListNode* slow = head;
    bool isCircle = false;
    while(fast && fast ->next){
        fast = fast->next->next;
        slow = slow ->next;
        if (fast == slow) {
            isCircle = true;
            break;
        }
    }
    if(!isCircle) return NULL;
    /*Reset fast to head and find the start point*/
    fast = head;
    while(fast !=slow){  // not been null
        slow = slow->next;
        fast = fast->next;
    }
    return slow;
}
