package org.practice.design.lfu_cache;

public class DLList {

    Node start;
    Node end;
    int size;

    public DLList() {
        this.start = new Node(0,0);
        this.end = new Node(0,0);
        this.size = 0;
        start.next = end;
        end.prev = start;
    }

    public void insertAtStart(Node node) {
        start.next.prev = node;
        node.next = start.next;
        start.next = node;
        node.prev = start;
        this.size++;
    }

    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        this.size--;
    }

    public Node removeFromEnd() {
        if (isEmpty()) return null;
        Node node = end.prev;
        remove(node);
        return node;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

}
