package com.github.zack.use.java.base.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * CLH 锁简介
 * CLH 锁（Craig, Landin, and Hagersten Lock）是一种 自旋锁，基于队列（FIFO）思想实现。
 * 它通过线程排队的方式，减少锁竞争，适合用于高并发场景下的线程同步。CLH 锁的特点是使用 隐式队列 管理线程排队，并且线程通过自旋等待其前驱节点释放锁。
 * <p>
 * CLH 锁的主要目标是：
 * 1. 提供一个公平的锁机制（FIFO）。
 * 2. 降低线程之间的竞争，提高性能。
 * <p>
 * CLH 锁的工作原理
 * 1. 队列节点（Node）： 每个线程都有一个关联的节点，表示线程的锁状态（是否持有锁）。
 * 节点分为两种状态：
 * true：当前线程还在持有锁。
 * false：当前线程已释放锁。
 * 2. 隐式队列：
 * CLH 锁使用一个链表来组织所有等待的线程。
 * 每个线程通过一个指针指向其前驱节点，不需要显式的队列结构。
 * 3. 自旋：
 * 线程会不断地检查其前驱节点的状态。
 * 如果前驱节点释放了锁，当前线程就可以尝试获取锁。
 * 4. 公平性：
 * 线程按顺序排队，确保锁的获取是 FIFO。
 *
 * @author zack
 * @since 2024/11/26
 */
public class CLHLock {

    static class Node {
        // 当前节点是否持有锁， true: 持有锁
        volatile boolean locked = false;
    }

    // 尾结点
    private final AtomicReference<Node> tail;
    // 当前节点
    private final ThreadLocal<Node> currentNode;
    // 前一个节点
    private final ThreadLocal<Node> prevNode;

    public CLHLock() {
        this.tail = new AtomicReference<>(new Node());
        this.currentNode = ThreadLocal.withInitial(Node::new);
        this.prevNode = new ThreadLocal<>();
    }

    public void lock() {
        Node node = currentNode.get();
        node.locked = true;
        Node prev = tail.getAndSet(node);
        prevNode.set(prev);
        // 自旋等待前驱节点释放锁
        while (prev.locked) ;
    }

    public void unlock() {
        Node node = currentNode.get();
        node.locked = false;
        // 重置当前线程的节点
        currentNode.set(new Node());
    }


    public static void main(String[] args) {
        CLHLock lock = new CLHLock();
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " Prev lock");
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " acquired the lock");
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " released the lock");
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
    }

}
