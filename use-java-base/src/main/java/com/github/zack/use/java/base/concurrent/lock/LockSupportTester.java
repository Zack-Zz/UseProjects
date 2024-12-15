package com.github.zack.use.java.base.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;

/**
 * @author zack
 * @since 2024/12/15
 */
public class LockSupportTester {

    public static void main(String[] args) {
        seqWriteABC();
    }

    /**
     * 三个线程分别输出 A、B、C
     * 按顺序输出
     */
    public static void seqWriteABC() {
        Object blocker = new Object();

        Consumer<String> consumer = str -> {
            while (true) {
                LockSupport.park(blocker);
                if (Thread.currentThread().isInterrupted()) {
                    throw new RuntimeException(Thread.currentThread().getName() + " has been interrupted.");
                }
                System.out.println(str);
            }
        };

        Thread threadA = new Thread(() -> {
            consumer.accept("A");
        }, "ThreadA");
        Thread threadB = new Thread(() -> {
            consumer.accept("B");
        }, "ThreadB");
        Thread threadC = new Thread(() -> {
            consumer.accept("C");
        }, "ThreadC");

        threadA.start();
        threadB.start();
        threadC.start();

        new Thread(() -> {
            int i = 0;
            while (true) {
                if (i % 3 == 0) {
                    LockSupport.unpark(threadA);
                } else if (i % 3 == 1) {
                    LockSupport.unpark(threadB);
                } else {
                    LockSupport.unpark(threadC);
                }
                i++;
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        }).start();


    }
}
