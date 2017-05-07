/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
 
int MAX(int p, int q){
     return p>q?p:q;
 }
struct ListNode* mergeTwoLists(struct ListNode* l1, struct ListNode* l2) {
    if(!l1) return l2;
    if(!l2) return l1;
    struct ListNode *head = (struct ListNode*)malloc(sizeof(struct ListNode));
    struct ListNode *tmp = head;
    while(l1 && l2){
        if(l1->val <= l2->val){
            tmp->next = l1;
            l1 = l1->next;
        }else{
            tmp ->next = l2;
            l2 = l2->next;
        }
        tmp = tmp->next;
    } 
    if(l1){
        tmp->next = l1;
    }
    if(l2){
        tmp->next = l2;
    }
    return head ->next;
}
/*Merge Klist*/
struct ListNode* merge(struct ListNode* left, struct ListNode* right){
    if(!left) return right;
    if(!right) return left;
    
    struct ListNode* dummy = (struct ListNode* )malloc(sizeof(struct ListNode));
    struct ListNode* cur = dummy;
    while(left && right){
        if(left->val < right->val) {
            cur->next = left;
            left = left->next;
        }else{
            cur->next = right;
            right = right->next;
        }
        cur = cur->next;
    }
    if(left) cur->next = left;
    if(right) cur->next = right;
    return dummy->next;
}
/*Divide*/
struct ListNode* partition(struct ListNode** lists, int start, int end){
    if (start == end) return lists[start];
    if (start<end){
        int mid = start + (end -start)/2;
        struct ListNode* l1 = partition(lists, start, mid);
        struct ListNode* l2 = partition(lists, mid+1, end);
        return merge(l1, l2);
    }else{
        return NULL;
    }
}
struct ListNode* mergeKLists(struct ListNode** lists, int listsSize) {
    return partition(lists,0,listsSize-1);
}
