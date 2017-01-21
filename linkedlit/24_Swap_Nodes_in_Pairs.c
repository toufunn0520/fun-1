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


/*2  more complicated */
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* swapPairs(struct ListNode* head) {
    if(!head || !(head->next)) return head;
    /* Divide the list into odd and even list*/
    struct ListNode* odd = head;
    struct ListNode* even = head->next;
    struct ListNode* headeven = head->next;
    struct ListNode* cur = even->next;

    int i = 1;
    while(cur){
        if(i%2){
            odd-> next = cur;
            odd = odd->next;
        }else{
            even ->next = cur;
            even = even->next;
        }
        cur = cur ->next;
        i++;
    }
    /*Combined those two lists*/
    odd = head;
    even = headeven->next;
    cur = headeven;
    i = 1;
    while(cur){
      if (i%2){
          cur->next = odd;
          if(odd)odd = odd ->next;
      } else{
          cur->next = even;
          if(even)even= even ->next; 
      }
      cur = cur->next;
      i++;
    }
    return headeven;
}
/*Recursive methods*/
{
       if ((head == null)||(head.next == null))
            return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }
