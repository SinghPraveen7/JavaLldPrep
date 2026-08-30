package org.practice.design.lru_cache;

public class Driver {

    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(3);
        lruCache.insert("First", 1);
        lruCache.insert("Second", 2);
        lruCache.insert("third", 3);
        System.out.println(lruCache.get("First"));
        lruCache.insert("fourth", 4);
        System.out.println(lruCache.get("Second"));

    }

}
