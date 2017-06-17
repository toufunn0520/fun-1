
class TrieNode {
public:
    TrieNode* child[26];
    bool leaf;
    TrieNode(bool isLeaf) {
        memset(child, 0, sizeof(TrieNode*)*26);
        leaf = isLeaf;
    }
};

class WordDictionary {
public:
    /* Initialize your data structure here. */
    WordDictionary() {
        root = (TrieNode*)malloc(sizeof(TrieNode*));
        root = new TrieNode(false);
    }
    
    /** Adds a word into the data structure. */
    void addWord(string word) {
        int size = word.size();
        TrieNode* tmp = root;
        for(char c: word) {
            if ((tmp->child)[c-'a'] == NULL) {
                (tmp->child)[c-'a'] = new TrieNode(false);
            }
            tmp = (tmp->child)[c-'a'];
        }
        tmp->leaf = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    bool search(string word) {
        return internal(word.c_str(), root);
    }

private:
    TrieNode* root;
    bool internal(const char* word, TrieNode *r) {
        int size = strlen(word);
        int i = 0;
        TrieNode* tmp = r;
        while ( i < size) {
            if(!tmp) break;
            if (word[i] == '.') {
                TrieNode* cur = tmp;   // stupid iterate its kids needs to save itself
                for (int j = 0; j<26; j++) {
                    tmp = (cur->child)[j];
                    if (internal(&word[i+1], tmp) ) {
                        return true;
                    }
                    tmp = NULL;    // since all possibilities is checked just get out of the while or directly return false
                }
            } else {
                tmp = (tmp->child)[word[i]-'a']; 
            }
            i++;
        }
        return tmp && tmp->leaf;
    }
};

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * bool param_2 = obj.search(word);
 */
