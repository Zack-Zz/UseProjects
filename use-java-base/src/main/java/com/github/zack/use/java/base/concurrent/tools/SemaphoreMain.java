package com.github.zack.use.java.base.concurrent.tools;

import java.util.concurrent.Semaphore;

/**
 * 控制对共享资源的访问线程数量
 * <p>
 * 初始化时指定许可数（permits），线程必须获得许可才能继续执行。
 * 通过 acquire() 获取许可，通过 release() 释放许可。
 * <p>
 * 典型场景:
 * 限流，限制并发线程数。
 * 控制对资源的公平访问。
 *
 * @author zhouze
 * @date 2024/12/12
 */
public class SemaphoreMain {

    public static void main(String[] args) {

        int permits = 2; // 同时允许2个线程访问
        Semaphore semaphore = new Semaphore(permits);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire(); // 获取许可
                    System.out.println(Thread.currentThread().getName() + " acquired a permit.");
                    Thread.sleep(1000); // 模拟任务
                    System.out.println(Thread.currentThread().getName() + " released a permit.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // 释放许可
                }
            }).start();
        }

    }
}
