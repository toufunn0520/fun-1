package category.tree.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    private char value;

    boolean isLeaf;

    Map<Character, TrieNode> children;

    public TrieNode() {

    }

    public TrieNode(char value) {
        this.value = value;
        this.children = new HashMap<Character, TrieNode>();
    }
}
