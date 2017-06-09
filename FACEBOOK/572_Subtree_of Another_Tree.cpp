class Solution {
public:

    bool isSame(TreeNode* s, TreeNode* t) {
        if (!s && !t) return true;
        if( !s || !t) return false;
        if (s->val != t->val ) {return false;}
        if (s->val == t->val) return (isSame(s->left, t->left) && isSame(s->right, t->right));
    }
    
    bool isSubtree(TreeNode* s, TreeNode* t) {
        queue<TreeNode*> tmp;      //key
        tmp.push(s);
        while(!tmp.empty()) {
            TreeNode* cur= tmp.front();
            tmp.pop();    // don't forget
            if(isSame(cur, t)) return true;
            if(cur->left) tmp.push(cur->left);
            if(cur->right) tmp.push(cur->right);
        }
        return false;
    }
};
