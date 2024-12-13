package com.github.zack.use.java.base.collection.structure.ring;

/**
 * @author zack
 * @since 2024/12/13
 */
public class RingBuffer<T> {


    private final Object[] buffer;
    private int head = 0; // 头部索引
    private int tail = 0; // 尾部索引
    private int size = 0; // 当前元素数量
    private final int capacity; // 容量

    public RingBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new Object[capacity];
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T item) {
        if (isFull()) {
            throw new IllegalStateException("RingBuffer is full");
        }
        buffer[tail] = item;
        tail = (tail + 1) % capacity;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("RingBuffer is empty");
        }
        T item = (T) buffer[head];
        buffer[head] = null; // 清理数据
        head = (head + 1) % capacity;
        size--;
        return item;
    }

    public int size() {
        return size;
    }


}
