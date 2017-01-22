/**
 * Definition for singly-linked list with a random pointer.
 * struct RandomListNode {
 *     int label;
 *     struct RandomListNode *next;
 *     struct RandomListNode *random;
 * };
 */
struct RandomListNode *copyRandomList(struct RandomListNode *head) {
    if(!head) return head;
    struct RandomListNode *cur = head;
    struct RandomListNode* tmp = NULL;
    while(cur){
        struct RandomListNode* next = cur->next;
        tmp= (struct RandomListNode* )malloc(sizeof(struct RandomListNode));
        cur->next = tmp ;
        tmp ->label = cur->label;
        tmp ->next = next;
        tmp->random = NULL;
        cur = next;
    }
    
    /*update random pointer*/
    cur = head;
    while(cur){
       struct RandomListNode* orig_next = cur->next->next;
       if(cur->random){
           cur->next->random = cur->random->next;
       }
       cur = orig_next;
    }
    
    /*Finally can remove*/
    cur = head;
    tmp = head->next;
    while(cur){
        struct RandomListNode* deepcopy = cur->next;
        cur->next = deepcopy ->next;
        if(cur->next) deepcopy->next = cur->next->next;
        cur = cur->next;

    }
    return tmp;
}
