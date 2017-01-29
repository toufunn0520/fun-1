/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

struct ListNode* reverseBetween(struct ListNode* head, int m, int n) {
    if(!head) return head;
    
    /*NO need for safety check of m and n*/
    struct ListNode* dummy = malloc(sizeof(struct ListNode));
    struct ListNode* newstart = dummy;
    int i = 0;
    dummy->next = head;
    struct ListNode* start = NULL;
    
    while (i<m-1) {
        newstart = newstart->next;
        i++;
    }
    /*start should be "m"*/
    start = newstart->next;
    struct ListNode* cur = start;
    struct ListNode* prev = NULL;
    struct ListNode* next = NULL;
    
    while(i<n && cur){
        next = cur->next;
        cur->next = prev;
        prev = cur;
        cur = next;
        i++;
    }

    newstart->next = prev;  //1->4
    if(start) start->next = next; //2->5
    return dummy->next;
}


/*1 Better*/
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* reverseBetween(struct ListNode* head, int m, int n) {
    //previous node of m
    struct ListNode* dummy = (struct ListNode*)malloc(sizeof(struct ListNode));
    dummy->next = head;
    struct ListNode* start = dummy;
    struct ListNode* end = head;

    for(int i =0; i<m-1; i++){
        start = start ->next;
    }
    for(int i =0; i<n-1; i++){
        end = end ->next;
    }
    struct ListNode *node = start->next;
    struct ListNode *tmp= NULL;
    struct ListNode *prev= end->next;
    
    //printf("HHH\n");
    for(int i =0; i<n-m+1; i++){
        //printf("HHH node%d prev%d\n",node->val,prev?prev->val:123);

        tmp = node->next;
        node->next = prev;
        prev = node;
        node = tmp;
        printf("OK\n");

    }
    start->next = prev;
    return dummy->next;   
}
