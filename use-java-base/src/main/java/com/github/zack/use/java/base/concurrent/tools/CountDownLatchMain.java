package com.github.zack.use.java.base.concurrent.tools;

import java.util.concurrent.CountDownLatch;

/**
 * 用于让一个或多个线程等待其他线程完成操作
 * <p>
 * 初始化时指定一个计数器值（count）
 * 其他线程调用 countDown() 方法使计数器减 1
 * 当计数器值变为 0 时，所有等待的线程会继续执行
 * <p>
 * 典型场景:
 * 等待多个线程完成任务后再执行某操作。
 * 主线程等待多个子线程完成初始化或任务。
 *
 * @author zhouze
 * @date 2024/12/12
 */
public class CountDownLatchMain {

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is working...");
                    Thread.sleep(2000); // 模拟任务
                    System.out.println(Thread.currentThread().getName() + " finished.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        new Thread(() -> {

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("worker thread running end. " + Thread.currentThread().getName());
        }).start();
        System.out.println("Main thread go." + Thread.currentThread().getName());
    }

}
