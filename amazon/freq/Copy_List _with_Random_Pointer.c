/**
 * Definition for singly-linked list with a random pointer.
 * struct RandomListNode {
 *     int label;
 *     RandomListNode *next, *random;
 *     RandomListNode(int x) : label(x), next(NULL), random(NULL) {}
 * };
 */
class Solution {
public:
    RandomListNode *copyRandomList(RandomListNode *head) {
        if (head == NULL) return head;
        RandomListNode *dummy = new RandomListNode(-1);
        RandomListNode *cur = head;
        RandomListNode *tmp = dummy;
        
        unordered_map<RandomListNode*, RandomListNode*>map;
        while(cur){
            tmp->next = new RandomListNode(cur->label);
            map[cur] = tmp->next; 
            cur = cur->next;
            tmp = tmp->next;
        }
        cur = head;
        tmp = dummy->next;
        while(cur){
            tmp->random = map[cur->random];
            cur = cur ->next;
            tmp = tmp ->next;
        }
        return dummy->next;
        
    }
};
