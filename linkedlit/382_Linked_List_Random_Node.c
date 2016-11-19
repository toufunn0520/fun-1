/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
typedef struct {
    
    struct ListNode *head;
    
} Solution;

/** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
Solution* solutionCreate(struct ListNode* head) {
    
    if(!head) return NULL;
    Solution *sol = (Solution *) malloc(sizeof(Solution));
    sol->head = head;
    return sol;
    
}

/** Returns a random node's value.
选取的概率为(1/2)* （2/3）*（3/4）* ……….. (n-1) / n = 1/n   （选取第2个数在长度为2的时候为1/2，其他的都不要选)
*/
int solutionGetRandom(Solution* obj) {
    if(!obj && !(obj->head)) return 0;
    struct ListNode *tmp = obj->head;
    int ans = 0;
    for(int len = 1; tmp!=NULL; tmp= tmp->next){
         if (rand() % len == 0) {
             ans = tmp->val;
         }
         len++;
    }
    return ans;
}

void solutionFree(Solution* obj) {
    if(!obj) return;
    if(obj->head) {
        free(obj->head);
        obj->head = NULL;
    }
    
    free(obj);
    obj = NULL;
    return;
}

/**
 * Your Solution struct will be instantiated and called as such:
 * struct Solution* obj = solutionCreate(head);
 * int param_1 = solutionGetRandom(obj);
 * solutionFree(obj);
 */
