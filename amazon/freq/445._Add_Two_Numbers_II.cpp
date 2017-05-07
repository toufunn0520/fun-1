class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        stack<int> r1;
        stack<int> r2;
        int sum = 0;
        ListNode* curl1 = l1;
        ListNode* curl2 = l2;
        
        while(curl1){
            r1.push(curl1->val);
            curl1 = curl1->next;
        }
        
        while(curl2){
            r2.push(curl2->val);
            curl2 = curl2->next;
        }
        
        ListNode* res = new ListNode(0);
        while(!r1.empty() || !r2.empty() || sum){
            if(!r1.empty()) {sum += r1.top(); r1.pop();}
            if(!r2.empty()) {sum += r2.top(); r2.pop();}
            ListNode* cur = new ListNode(sum%10);
            cur->next = res->next;
            res->next = cur;
            sum =sum/10;
        }
        
        return res->next;
    }
};
