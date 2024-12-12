package com.github.zack.use.java.base.concurrent.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 让一组线程在某个“屏障”处等待，直到所有线程都到达后再继续执行
 * <p>
 * 可以被重复使用（循环）。
 * 在所有线程到达屏障点时可以执行一个可选的任务（Runnable）。
 * <p>
 * 多线程分阶段任务，每个阶段需要等待所有线程完成后进入下一阶段
 *
 * @author zhouze
 * @date 2024/12/12
 */
public class CyclicBarrierMain {

    public static void main(String[] args) throws InterruptedException {
        Long now = System.currentTimeMillis();

        int threadCount = 3;
        CyclicBarrier barrier = new CyclicBarrier(threadCount,
                () -> {
                    System.out.println("[" + diff(now) + "]" + "All threads reached the barrier.[Start] Proceeding to next phase...");

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("[" + diff(now) + "]" + "All threads reached the barrier.[End]  Proceeding to next phase...");
                });

        for (int i = 0; i < threadCount * 3; i++) {
            if (i % 3 == 0) {
                Thread.sleep(3000);
            }
            new Thread(() -> {
                try {
                    System.out.println("[" + diff(now) + "]" + Thread.currentThread().getName() + " is working...");
                    Thread.sleep(1000); // 模拟任务
                    System.out.println("[" + diff(now) + "]" + Thread.currentThread().getName() + " reached the barrier.");
                    barrier.await(); // 等待其他线程
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println("[" + diff(now) + "]" + "Main thread running. " + Thread.currentThread().getName());
    }

    public static String diff(Long start) {
        long seconds = (System.currentTimeMillis() - start) / 1000;
        return String.valueOf(seconds);
    }
}
