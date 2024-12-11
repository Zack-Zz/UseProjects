package com.github.zack.use.java.base.collection;

import java.util.LinkedHashSet;

/**
 * @author zack
 * @since 2024/12/11
 */
public class LRULinkedSetCache {


    private final int capacity;
    private final LinkedHashSet<Integer> cache;

    public LRULinkedSetCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashSet<>(capacity);
    }

    public void access(int item) {
        if (cache.contains(item)) {
            cache.remove(item); // 移动到最后
        } else if (cache.size() == capacity) {
            int first = cache.iterator().next();
            cache.remove(first); // 删除最旧的元素
        }
        cache.add(item);
    }

    public void display() {
        System.out.println(cache);
    }

    public static void main(String[] args) {
        LRULinkedSetCache lru = new LRULinkedSetCache(3);
        lru.access(1);
        lru.access(2);
        lru.access(3);
        lru.display(); // 输出：[1, 2, 3]

        lru.access(2); // 访问 2
        lru.display(); // 输出：[1, 3, 2]

        lru.access(4); // 添加 4（容量满了，移除最旧的 1）
        lru.display(); // 输出：[3, 2, 4]
    }
}
