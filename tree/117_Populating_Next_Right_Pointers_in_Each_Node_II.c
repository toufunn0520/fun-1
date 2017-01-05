 void connect(struct TreeLinkNode *root) {
     if(!root) return root;
     struct TreeLinkNode* saved = root;
     
     while(saved!=NULL){
              struct TreeLinkNode* head = (struct TreeLinkNode*)malloc(sizeof(struct TreeLinkNode));
              head->next = NULL;    // !!!key
              struct TreeLinkNode* nextlevel = head;
              while(saved!=NULL){
                  if(saved->left != NULL){
                      nextlevel->next = saved->left;
                      nextlevel = nextlevel->next;
                  }
                  if(saved->right != NULL){
                      nextlevel->next = saved->right;
                      nextlevel = nextlevel->next;
                  }
                  saved = saved->next;
              }
              saved = head->next;
      }
}
