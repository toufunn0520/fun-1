package category.tree.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * [Leetcode ] https://leetcode.com/problems/add-and-search-word-data-structure-design/
 * 
 * <pre>
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can
 * represent any one letter.
 *
 * For example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * </pre>
 */
public class WordDictionary {

    class Node {

        char c;

        Map<Character, Node> children;

        boolean isLeaf;

        public Node(char c) {
            this.c = c;
            children = new HashMap<Character, Node>();
        }
    }

    // Returns if the word is in the data structure. A word could

    // contain the dot character '.' to represent any one letter.

    private Node root = new Node(' ');

    public void addWord(String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        Node curNode = root;
        for (char curChar : word.toCharArray()) {
            if (curNode.children.containsKey(curChar)) {
                curNode = curNode.children.get(curChar);
            } else {
                Node newNode = new Node(curChar);
                curNode.children.put(curChar, newNode);
                curNode = newNode;
            }
        }

        curNode.isLeaf = true;
    }

    private boolean dfs(Node root, char[] charArray, int index) {
        Node curNode = root;

        for (int i = index; i < charArray.length; i++) {
            if (charArray[i] == '.') {
                for (Node child : curNode.children.values()) {
                    if (dfs(child, charArray, i + 1)) {
                        return true;
                    }
                }

                return false;
            } else if (curNode.children.containsKey(charArray[i])) {
                curNode = curNode.children.get(charArray[i]);
            } else {
                return false;
            }
        }

        return curNode.isLeaf;
    }

    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        char[] charArray = word.toCharArray();

        return dfs(root, charArray, 0);
    }
}
