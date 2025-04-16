package com.github.zack.use.java.base.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zack
 * @since 2024/11/29
 */
public class DemoTester {

    public static void main(String[] args) {
        Integer a = new Integer(126);
        Integer b = new Integer(126);
        Integer c = Integer.valueOf(126);
        Integer d = Integer.valueOf(126);

        System.out.println(a == b); // false
        System.out.println(b == c); // false
        System.out.println(c == d); // true

//        String phone = "+66 12312312";
//        String substring = phone.substring(3);
//        System.out.println(substring);

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
