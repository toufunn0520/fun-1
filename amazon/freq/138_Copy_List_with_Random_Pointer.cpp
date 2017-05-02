/**
 * Definition for singly-linked list with a random pointer.
 * struct RandomListNode {
 *     int label;
 *     RandomListNode *next, *random;
 *     RandomListNode(int x) : label(x), next(NULL), random(NULL) {}
 *     bool operator==(Node i) {
            if ( i.a==this->a && i.b==this->b &&i.c==this->c ) {
                return true;
            } else {
                return false;
            }
        }
 * };
 */
class Solution {
public:
       RandomListNode *copyRandomList(RandomListNode *head) {
        if (head == NULL) return head;
        unordered_map<RandomListNode*, RandomListNode*> map; 
        RandomListNode* dummy = new RandomListNode(-1);
        RandomListNode* tmp = dummy;
        RandomListNode* cur = head;
        while (cur) {
            RandomListNode* deepcopy = new RandomListNode(cur->label);
            map[cur] = deepcopy;
            /*Generate the chain*/
            tmp ->next = deepcopy;
            tmp = tmp ->next;
            cur = cur->next;
        }
        
        cur = head;
        tmp = dummy->next;
        while (cur) {
            tmp ->random = map[cur->random];
            tmp = tmp->next;
            cur = cur->next;
        }
        return dummy->next;
    }
};
