package org.practice.datastructures.tries;

public class TrieNode {

    TrieNode[] children;
    Boolean isEndNode;

    public TrieNode() {
        children = new TrieNode[26]; // Represent 26 alphabets
        isEndNode = false;
    }

}
