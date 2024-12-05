package com.github.zack.use.java.base.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zack
 * @since 2024/11/29
 */
public class DemoTester {

    public static void main(String[] args) {
        String phone = "+66 12312312";
        String substring = phone.substring(3);
        System.out.println(substring);

//        ReentrantLock lock = new ReentrantLock();
//
//        Runnable runnable = ()->{
//            lock.lock();
//            try {
//                // ..
//                try {
//                    Thread.sleep(100000 * 100);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            } finally {
//                lock.unlock();
//            }
//        };
//
//        Thread t1 = new Thread(runnable);
//        t1.start();
//        Thread t2 = new Thread(runnable);
//        t2.start();
    }
}
