#define POS(c)     ((int)c - (int)'a')
#define SUBTREE 26
struct TrieNode {
    struct TrieNode *child[SUBTREE];
    bool isleaf;
};

/** Initialize your data structure here. */
struct TrieNode* trieCreate() {
    struct TrieNode* root = (struct TrieNode*) malloc(sizeof(struct TrieNode));
    root->isleaf = false;
    for(int i =0; i<SUBTREE;i++) root->child[i] = NULL;
    return root;
}

/** Inserts a word into the trie. */
void insert(struct TrieNode* root, char* word) {
    int len = strlen(word);
    if(!len) return;
    struct TrieNode* cur= root;
    for(int i = 0; i<len; i++){
        if(!(cur->child[POS(word[i])])){
            cur->child[POS(word[i])] = trieCreate();
        }
    cur = cur->child[POS(word[i])];
    }
    cur->isleaf=true;
}

/** Returns if the word is in the trie. */
bool search(struct TrieNode* root, char* word) {
    int len = strlen(word);
    if(!len) return;
    struct TrieNode* cur= root;
    for(int i = 0; i<len; i++){
         if(!(cur->child[POS(word[i])])){
             return false;
        }
    cur = cur->child[POS(word[i])];
    }
    return cur->isleaf;
}

/** Returns if there is any word in the trie 
    that starts with the given prefix. */
bool startsWith(struct TrieNode* root, char* prefix) {
    int len = strlen(prefix);
    if(!len) return;
    struct TrieNode* cur= root;
    for(int i = 0; i<len; i++){
         if(!(cur->child[POS(prefix[i])])){
             return false;
        }
    cur = cur->child[POS(prefix[i])];
    }
    return true;
    
}

/** Deallocates memory previously allocated for the TrieNode. */
void trieFree(struct TrieNode* root) {
    if(!root) return;
    for(int i=0; i<SUBTREE; i++){
        trieFree(root->child[i]);
    }
    free(root);
}

// Your Trie object will be instantiated and called as such:
// struct TrieNode* node = trieCreate();
// insert(node, "somestring");
// search(node, "key");
// trieFree(node);
