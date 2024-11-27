package com.github.zack.use.java.base.concurrent.queue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zack
 * @since 2024/11/27
 */
public class BlockedQueue<T> {

    private final ReentrantLock lock = new ReentrantLock();

    // 队列未满条件
    private final Condition notFull = lock.newCondition();
    // 队列不为空条件
    private final Condition notEmpty = lock.newCondition();

    private volatile Node<T> tail;
    private volatile Node<T> cur;

    private final int capacity;

    transient AtomicInteger size = new AtomicInteger(0);

    public BlockedQueue(int capacity) {
        this.capacity = capacity;
    }

    void enqueue(T t) throws InterruptedException {
        lock.lock();
        try {
            while (size.get() >= capacity) {
                notFull.await();
            }
            size.incrementAndGet();
            Node<T> last = new Node<>(t);
            if (tail == null) {
                tail = last;
            } else {
                cur = tail;
                last.setPrev(tail);
                cur.setNext(last);
                tail = last;
                tail.setPrev(cur);
            }

            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    T dequeue() throws InterruptedException {
        T r;
        lock.lock();
        try {
            while (size.get() <= 0) {
                notEmpty.await();
            }
            size.decrementAndGet();
            r = tail.getT();
            if (cur != null) {
                cur.setNext(null);
                tail = cur;
            }

            notFull.signal();
        } finally {
            lock.unlock();
        }
        return r;
    }

    public static void main(String[] args) {
        BlockedQueue<Integer> queue = new BlockedQueue<>(5);

        // 生产者线程
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    queue.enqueue(i);
                    System.out.println("Produced: " + i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 消费者线程
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    int item = queue.dequeue();
                    System.out.println("Consumed: " + item);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producer.start();
        consumer.start();
    }

}
