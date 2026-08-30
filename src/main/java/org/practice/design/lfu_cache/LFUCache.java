package org.practice.design.lfu_cache;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    int minFreq;
    int capacity;
    Map<Integer, Node> keyValueMap;
    Map<Integer, DLList> freqListMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyValueMap = new HashMap<>();
        freqListMap = new HashMap<>();
    }

    public int get(int key) {
        if (keyValueMap.containsKey(key)) {
            Node node = keyValueMap.get(key);
            updateFreqList(node);
            return node.value;
        }
        return -1;
    }

    private void updateFreqList(Node node) {
        int freq = node.freq;
        DLList list = freqListMap.get(freq);
        list.remove(node);
        if (this.minFreq == freq && list.isEmpty()) {
            this.minFreq++;
        }
        DLList newList = freqListMap.getOrDefault(freq + 1, new DLList());
        node.freq = freq + 1;
        newList.insertAtStart(node);
        freqListMap.put(freq + 1, newList);
    }


    public void put(int key, int value) {
        if (keyValueMap.containsKey(key)) {
            Node node = keyValueMap.get(key);
            node.value = value;
            updateFreqList(node);
            return;
        }
        if (keyValueMap.size() == this.capacity) {
            removeLeastFreqData();
        }
        addData(key, value);
    }

    private void removeLeastFreqData() {
        DLList list = freqListMap.get(this.minFreq);
        Node node = list.removeFromEnd();
        keyValueMap.remove(node.key);
        if (list.isEmpty()) this.minFreq++;
    }

    private void addData(int key, int value) {
        Node node = new Node(key, value);
        DLList list = freqListMap.getOrDefault(1, new DLList());
        list.insertAtStart(node);
        freqListMap.put(1, list);
        keyValueMap.put(key, node);
        this.minFreq = 1;
    }


}
