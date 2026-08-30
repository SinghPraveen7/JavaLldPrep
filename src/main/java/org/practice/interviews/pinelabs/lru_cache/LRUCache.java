package org.practice.interviews.pinelabs.lru_cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    Integer capacity;
    Node start;
    Node end;
    Map<String, Node> mapping;

    public LRUCache(Integer capacity) {
        this.capacity = capacity;
        start = new Node("-1", -1);
        end = new Node("-1", -1);
        mapping = new HashMap<>();
        start.next = end;
        end.prev = start;
    }

    public Integer get(String key) {
        if (!mapping.containsKey(key)) return -1;
        Node currentNode = mapping.get(key);
        detachNodeFromCurrentPosition(currentNode);
        insertNodeToTheStart(currentNode);
        return currentNode.value;
    }

    public void insert(String key, Integer value) {
        if (mapping.containsKey(key)) {
            Node currentNode = mapping.get(key);
            currentNode.value = value;
            detachNodeFromCurrentPosition(currentNode);
            insertNodeToTheStart(currentNode);
            return;
        }
        if (this.capacity > 0) {
            Node newNode = new Node(key, value);
            mapping.put(key, newNode);
            insertNodeToTheStart(newNode);
            this.capacity = this.capacity - 1;
        } else {
            Node nodeToBeRemoved = removeFromEnd();
            mapping.remove(nodeToBeRemoved.key);
            Node newNode = new Node(key, value);
            mapping.put(key, newNode);
            insertNodeToTheStart(newNode);
        }
    }

    // start 1 2 3 4 end
    // Least recently used data
    private Node removeFromEnd() {
        Node prevToEnd = this.end.prev;
        this.end.prev = prevToEnd.prev;
        prevToEnd.prev.next = this.end;
        return prevToEnd;
    }

    private void insertNodeToTheStart(Node currentNode) {
        currentNode.next = start.next;
        currentNode.prev = start;
        start.next = currentNode;
        currentNode.next.prev = currentNode;
    }

    private void detachNodeFromCurrentPosition(Node currentNode) {
        currentNode.prev.next = currentNode.next;
        currentNode.next.prev = currentNode.prev;
    }


}
