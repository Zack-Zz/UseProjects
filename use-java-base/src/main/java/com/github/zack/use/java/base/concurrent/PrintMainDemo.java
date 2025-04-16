package com.github.zack.use.java.base.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 *
 * @author zack
 * @since 2025/4/13
 */
public class PrintMainDemo {

    private static volatile AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) {

        Runnable runnable = () -> {
            while (true) {
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + ": " + num.get());
            }
        };

        Thread threadA = new Thread(runnable);
        threadA.setName("threadA");
        Thread threadB = new Thread(runnable);
        threadB.setName("threadB");

        threadA.start();
        threadB.start();

        while (true) {
            if (num.get() == 0) {
                LockSupport.unpark(threadA);
            }

            if (num.get() % 2 == 0) {
                LockSupport.unpark(threadA);
            } else {
                LockSupport.unpark(threadB);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int i = num.incrementAndGet();
            if (i > 200) {
                break;
            }
        }

    }

}
