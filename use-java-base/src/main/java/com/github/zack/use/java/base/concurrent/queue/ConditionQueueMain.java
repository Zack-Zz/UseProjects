package com.github.zack.use.java.base.concurrent.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zack
 * @since 2024/12/15
 */
public class ConditionQueueMain {

    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static boolean conditionMet = false;

    public static void main(String[] args) {
        Thread waiter = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Condition met, starting");
                while (!conditionMet) {
                    System.out.println("Waiting for condition...");
                    condition.await(); // 进入条件队列
                }
                System.out.println("Condition met, proceeding...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });

        Thread signaler = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Signaling condition...");
                conditionMet = true;
                condition.signal(); // 唤醒条件队列中的一个线程
            } finally {
                lock.unlock();
            }
        });

        waiter.start();
        try {
            Thread.sleep(1000); // 确保 waiter 线程进入条件队列
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        signaler.start();
    }
}
