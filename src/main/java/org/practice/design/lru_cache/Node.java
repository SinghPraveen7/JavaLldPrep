package org.practice.design.lru_cache;

public class Node {

    String key;
    Integer value;

    Node next;
    Node prev;

    public Node(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

}
