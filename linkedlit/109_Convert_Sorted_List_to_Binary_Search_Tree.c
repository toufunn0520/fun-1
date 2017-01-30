/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
struct TreeNode* helper(struct ListNode* head){
     if(!head) return  NULL;
     
     struct ListNode* fast = head;
     struct ListNode* slow = head;
     struct ListNode* preslow = NULL;
     
     while(fast && fast->next){
         fast = fast->next->next;
         preslow = slow;
         slow = slow ->next;
     }
     
     if(preslow) preslow->next = NULL; //Import
     struct TreeNode * cur = malloc(sizeof(struct TreeNode));
     cur->val = slow->val;
     
     if (slow != head) { // prevent loop
         cur->left = helper(head); 
     }else{
         cur->left = NULL;
     }
     cur->right = helper(slow->next);
     return cur; 
}
struct TreeNode* sortedListToBST(struct ListNode* head) {
    return helper(head);
}
/*1st/

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
 /* not balanced 
 in-order
 */
struct ListNode* current = NULL;
int getLength (struct ListNode* head) {
    int size = 0;
    current = head;
    while(head){
        size++;
        head = head->next;
    }
    return size;
}

struct TreeNode* sortedListToBST_helper(struct ListNode* head, int size) {
    if (size ==0) return NULL;
    //left, node. right
    struct TreeNode* left = sortedListToBST_helper(head, size/2);
    struct TreeNode* root = (struct TreeNode*)malloc(sizeof(struct TreeNode));
    root->val = current ->val;
    current = current->next;
    struct TreeNode* right = sortedListToBST_helper(head, size- 1 -size/2);
    root->left = left;
    root->right = right;
    return root;

    
}
struct TreeNode* sortedListToBST(struct ListNode* head) {
 if (!head) return head;
 // get height
  int size = getLength(head);
  return sortedListToBST_helper(head, size);
}
