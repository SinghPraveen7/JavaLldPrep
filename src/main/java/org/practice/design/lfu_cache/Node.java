package org.practice.design.lfu_cache;

public class Node {

    int key;
    int value;
    int freq;
    Node next;
    Node prev;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.freq = 1;
    }

    public Node(int key, int value, int freq) {
        this.key = key;
        this.value = value;
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                ", freq=" + freq +
                '}';
    }
}
