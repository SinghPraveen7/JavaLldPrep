package org.practice.datastructures.tries;

public class Driver {
    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println("Insert apple");
        trie.insert("apple");
        System.out.println("Insert apps");
        trie.insert("apps");
        System.out.println("Insert bat");
        trie.insert("bat");
        System.out.println("Insert battery");
        trie.insert("battery");
        System.out.println("searching apple: " + trie.search("apple"));
        System.out.println("searching apps: " + trie.search("apps"));
        System.out.println("searching batt: " + trie.search("batt"));
        System.out.println("searching app: " + trie.search("app"));
        System.out.println("searching prefix app: " + trie.startsWith("app"));
        System.out.println("searching prefix batt: " + trie.startsWith("batt"));
        System.out.println("searching prefix abc: " + trie.startsWith("abc"));
        System.out.println("free searching a.pl.: " + trie.freeSearch("a.pl."));
        System.out.println("free searching ..p..: " + trie.freeSearch("..p.."));
        System.out.println("free searching b.tt..y: " + trie.freeSearch("b.tt..y"));
    }
}
