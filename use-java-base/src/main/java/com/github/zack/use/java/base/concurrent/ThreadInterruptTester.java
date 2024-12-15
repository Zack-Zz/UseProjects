package com.github.zack.use.java.base.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * 关于中断的一些测试，那些状态下会因为中断退出当前状态(阻塞或等待状态)
 *
 * @author zack
 * @since 2024/12/15
 */
public class ThreadInterruptTester {

    public static void main(String[] args) {
//        testInterruptInBlockedState();
//
//        testInterruptWithJoin();

        testInterruptWithWait();

//        testInterruptWithRunning();

    }


    public static void testInterruptInBlockedState() {
        Object lock = new Object();
        Thread threadA = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("Thread A interrupted");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            // 因为其他线程获取到锁，这里不论该线程是否已经中断，都会继续阻塞，直到线程退出阻塞状态
            synchronized (lock) {
                System.out.println("Thread B acquired the lock");

                System.out.println(Thread.currentThread().isInterrupted());
                if (Thread.currentThread().isInterrupted()) {
                    // 这里不会进入阻塞，会直接返回
                    LockSupport.park();

                    System.out.println("park end");
                }
            }
        });

        threadA.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        threadB.start();
        System.out.println("Thread B state:" + threadB.getState());
        threadB.interrupt();
    }


    /**
     * join 退出
     */
    public static void testInterruptWithJoin() {
        Thread thread = new Thread(() -> {
            System.out.println("JOIN Thread state:" + Thread.currentThread().getState());
            try {
                Thread.sleep(10 * 1000); // 线程进入 TIMED_WAITING 状态
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted during sleep");
            }
        });
        thread.start();
        thread.interrupt(); // 唤醒线程，抛出 InterruptedException，状态变为 RUNNABLE。
    }

    /**
     * sleep 退出
     */
    public static void testInterruptWithWait() {
        Object lock = new Object();
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("WAIT Thread will wait. STATE:" + Thread.currentThread().getState());
                    lock.wait(); // 线程进入 WAITING 状态
                    System.out.println("WAIT Thread after wait. STATE:" + Thread.currentThread().getState());

                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted during wait");
                }
            }
        });
        thread.start();
        thread.interrupt(); // 唤醒线程，抛出 InterruptedException，状态变为 RUNNABLE。

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        new Thread(() -> {
//            synchronized (lock) {
//
//                System.out.println("Notify Thread will notify. STATE:" + Thread.currentThread().getState());
//                lock.notify();
//                System.out.println("Notify Thread after notify. STATE:" + Thread.currentThread().getState());
//            }
//        }).start();
    }

    /**
     * RUNNING 状态不变
     */
    public static void testInterruptWithRunning() {
        Thread thread = new Thread(() -> {
            System.out.println("Thread is running... STATE:" + Thread.currentThread().getState());
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Thread is running in while... STATE:" + Thread.currentThread().getState());
            }
            System.out.println("Thread interrupted, exiting... STATE:" + Thread.currentThread().getState());
        });

        thread.start();
        thread.interrupt();
    }

}
