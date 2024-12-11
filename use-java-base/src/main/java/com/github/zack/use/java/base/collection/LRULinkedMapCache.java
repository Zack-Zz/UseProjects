package com.github.zack.use.java.base.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zack
 * @since 2024/12/11
 */
public class LRULinkedMapCache<K, V> extends LinkedHashMap<K, V> {


    private final int capacity;

    public LRULinkedMapCache(int capacity) {
        super(capacity, 0.75f, true);  // 强制accessOrder为true
        this.capacity = capacity;
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }


    public static void main(String[] args) {
        LRULinkedMapCache<Integer, String> cache = new LRULinkedMapCache<>(3);
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        cache.get(1); // 访问键 1
        cache.put(4, "Four"); // 插入键 4，触发移除最旧的键 2
        System.out.println(cache); // 输出：{3=Three, 1=One, 4=Four}
    }
}
