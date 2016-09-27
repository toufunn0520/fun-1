package category.tree.trie;

import java.util.HashMap;

/**
 * [Leetcode 208] https://leetcode.com/problems/implement-trie-prefix-tree/
 * 
 * <pre>
 * Implement a trie with insert, search, and startsWith methods.
 * </pre>
 * 
 * @author boyi
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
        root.children = new HashMap<Character, TrieNode>();
    }

    /**
     * Note: this API only used by binary strings. Returns the longest matched prefix of the word.
     *
     * @param word
     * @return
     */
    public String closestMatchBinaryString(String word) {
        TrieNode current = root;

        StringBuilder matchedPrefix = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
                matchedPrefix.append(c);
            } else {
                char oppositeChar = (char) ('1' + '0' - c);
                current = current.children.get(oppositeChar);
                matchedPrefix.append(oppositeChar);
            }
        }

        return matchedPrefix.toString();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                TrieNode newNode = new TrieNode(c);
                current.children.put(c, newNode);
                current = newNode;
            }
        }
        current.isLeaf = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                return false;
            }
        }

        if (current.isLeaf == true) {
            return true;
        } else {
            return false;
        }
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode current = root;

        for (char c : prefix.toCharArray()) {
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                return false;
            }
        }

        return true;
    }
}
