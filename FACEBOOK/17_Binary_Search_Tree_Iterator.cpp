class BSTIterator {
public:
    BSTIterator(TreeNode *root) {
        pushAll(root);
    }

    /** @return whether we have a next smallest number */
    bool hasNext() {
        return !nodestack.empty();
    }

    /** @return the next smallest number */
    int next() {
        TreeNode* cur = nodestack.top();
        nodestack.pop();
        pushAll(cur->right);   //key
        return cur->val;
    }
    
private:
   stack<TreeNode*> nodestack;
    void pushAll(TreeNode* node){
        while(node){
            nodestack.push(node);
            node = node->left;
        }
    }
};
