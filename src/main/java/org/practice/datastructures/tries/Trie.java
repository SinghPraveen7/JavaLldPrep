package org.practice.datastructures.tries;

/**
 * Operation Time Complexity
 * Insert -> O(L)
 * Search -> O(L)
 * StartsWith -> O(L)
 * freeSearch -> O(26^N)
 *
 */
public class Trie {

    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode currentNode = this.root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (currentNode.children[index] == null) {
                currentNode.children[index] = new TrieNode();
            }
            currentNode = currentNode.children[index];
        }
        currentNode.isEndNode = true;
    }

    public boolean search(String word) {
        TrieNode currentNode = this.root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (currentNode.children[index] == null) {
                return false;
            }
            currentNode = currentNode.children[index];
        }
        return currentNode.isEndNode;
    }

    // for suffix search, we can save reverse word in another trie and then can search in that
    public boolean startsWith(String word) {
        TrieNode currentNode = this.root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (currentNode.children[index] == null) {
                return false;
            }
            currentNode = currentNode.children[index];
        }
        return true;
    }

    public boolean freeSearch(String word) {
        return dfs(word, 0, this.root);
    }

    private boolean dfs(String word, int index, TrieNode root) {
        if (root == null) return false;
        if (index == word.length()) return root.isEndNode;
        char ch = word.charAt(index);
        if (!(ch == '.')) {
            return dfs(word, index + 1, root.children[ch - 'a']);
        } else {
            for (int i = 0; i < 26; i++) {
                if (root.children[i] != null && dfs(word, index + 1, root.children[i])) {
                    return true;
                }
            }
        }
        return false;
    }

}
