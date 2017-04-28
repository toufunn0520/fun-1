class Solution {
public:
    TreeNode* helper(string s, int *index) {
        /*Always start with numbers*/
        int start = *index;
        
        if (s[start] == '-'){
            (*index)++;
        }
        while (isdigit(s[*index])){
            (*index)++;
        }
        
        int num = stoi(s.substr(start, (*index)-start));  // API
        TreeNode *cur = new TreeNode(num);
        if (s[*index] == '(') {
            (*index)++;
            cur->left = helper(s, index);
            (*index)++;
        }
        if (s[*index] == '(') {
            (*index)++;
            cur->right = helper(s, index);
            (*index)++;
        }
        
        return cur;
    }
    TreeNode* str2tree(string s) {
        if(!s.size()) return NULL;
        int index = 0;
        return helper(s, &index);
        
    }
};
