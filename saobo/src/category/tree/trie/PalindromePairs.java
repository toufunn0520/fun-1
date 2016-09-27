package category.tree.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PalindromePairs {

    class Node {

        Map<Character, Node> children;

        boolean isLeaf;

        char val;

        Node(char val) {
            this.val = val;
            this.isLeaf = false;
            children = new HashMap<Character, Node>();
        }

        @Override
        public String toString() {
            return String.valueOf(val) + ":" + isLeaf;
        }
    }

    class Trie {

        Node root;

        public Trie() {
            this.root = new Node(' ');
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }

            Node current = root;
            for (char c : word.toCharArray()) {
                if (current.children.containsKey(c)) {
                    current = current.children.get(c);
                } else {
                    Node newNode = new Node(c);
                    current.children.put(c, newNode);
                    current = newNode;
                }
            }
            current.isLeaf = true;
        }

        /**
         * Return the node of last match.
         *
         * @param word
         * @return
         */
        public Node searchPrefix(String word) {
            Node current = root;

            char[] wordChars = word.toCharArray();
            int i = 0;
            for (i = 0; i < wordChars.length; i++) {
                if (current.children.containsKey(wordChars[i])) {
                    current = current.children.get(wordChars[i]);
                } else {
                    break;
                }
            }

            return current;
        }

    }

    private static String getReverseWord(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    public static void main(String[] args) {

        List<String> words = Arrays.asList("aabbaaa", "bbaa", "a", "abbaa", "aabbaa", "aaabbaa", "aaabb");

        System.out.println(new PalindromePairs().getPalindromePairs(words));

    }

    /**
     * DFS search from node to any leaf.
     *
     * @param node
     * @param referenceNode
     *            This is used for prune.
     * @param subString
     * @param word
     * @param curWordInFront
     * @param palindromePairs
     */
    private void dfs(Node node, Node referenceNode, String subString, String word, boolean curWordInFront,
            Map<String, Set<String>> palindromePairs) {

        if (node.isLeaf && isPalindrome(subString) && !subString.isEmpty()) {
            String pairString;
            if (curWordInFront) {
                pairString = subString + word;
            } else {
                pairString = word + subString;
            }

            if (palindromePairs.containsKey(word)) {
                palindromePairs.get(word).add(pairString);
            } else {
                Set<String> pairs = new HashSet<String>();
                pairs.add(pairString);
                palindromePairs.put(word, pairs);
            }

            if (palindromePairs.containsKey(pairString)) {
                palindromePairs.get(pairString).add(word);
            } else {
                Set<String> pairs = new HashSet<String>();
                pairs.add(word);
                palindromePairs.put(pairString, pairs);
            }
        }

        for (Map.Entry<Character, Node> entry : node.children.entrySet()) {
            char newChar = entry.getKey();
            Node newNode = entry.getValue();

            if (referenceNode.children.containsKey(newChar)) {
                Node newStart = referenceNode.children.get(newChar);
                dfs(newNode, newStart, subString + newChar, word, curWordInFront, palindromePairs);
            }
        }
    }

    public Map<String, Set<String>> getPalindromePairs(List<String> words) {
        Map<String, Set<String>> palindromePairs = new HashMap<String, Set<String>>();

        if (words == null || words.size() == 0) {
            return palindromePairs;
        }

        Trie wordTrie = new Trie();
        Trie reversedTrie = new Trie();

        for (String word : words) {
            wordTrie.insert(word);
            reversedTrie.insert(getReverseWord(word));
        }

        for (String word : words) {
            Node node = reversedTrie.searchPrefix(word);
            if (node != null) {
                dfs(node, wordTrie.root, "", word, true, palindromePairs);
            }
        }

        for (String word : words) {
            Node node = wordTrie.searchPrefix(getReverseWord(word));
            if (node != null) {
                dfs(node, reversedTrie.root, "", word, false, palindromePairs);
            }
        }

        return palindromePairs;
    }

    private boolean isPalindrome(String word) {
        if (word.length() == 0) {
            return true;
        }

        char[] wordChar = word.toCharArray();
        int i = 0, j = wordChar.length - 1;
        while (i < j) {
            if (wordChar[i++] != wordChar[j--]) {
                return false;
            }
        }

        return true;
    }
}
