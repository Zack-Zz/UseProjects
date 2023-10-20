package com.github.zack.use.custom.circuit.breaker;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 统计相关
 *
 * @author zhouze
 * @date 2023/10/19
 */
public class Counter {


    /**
     * Closed 状态进入 Open状态 错误数阈值
     */
    private int failureCount = 5;

    /**
     * failureCount的统计时间窗口
     */
    private long failureTimeInterval = 2000;

    /**
     * 上一次失败的时间戳
     */
    private Long lastTime;

    /**
     * 当前错误数
     */
    private final AtomicInteger currentCount;


    /**
     * Half-Open状态的成功次数
     */
    private final AtomicInteger halfOpenSuccessCount;

    public Counter(int failureCount, long failureTimeInterval) {
        this.failureCount = failureCount;
        this.failureTimeInterval = failureTimeInterval;
        this.lastTime = System.currentTimeMillis();
        this.currentCount = new AtomicInteger(0);
        this.halfOpenSuccessCount = new AtomicInteger(0);
    }

    /**
     * 时间窗口内递增失败数
     * <p>
     * 当前时间-上一次失败时间大于时间窗口，则重置失败次数
     *
     * @return
     */
    public synchronized int incrFailureCount() {
        long current = System.currentTimeMillis();
        if ((current - lastTime) > failureTimeInterval) {
            lastTime = current;
            currentCount.set(0);
        }
        return currentCount.getAndIncrement();
    }

    /**
     * 递增半开数
     *
     * @return
     */
    public int incrSuccessHalfOpenCount() {
        return this.halfOpenSuccessCount.incrementAndGet();
    }

    public boolean failureThresholdReached(){
        return getCurCount() >= failureCount;
    }

    public int getCurCount(){
        return currentCount.get();
    }

    public synchronized void reset(){
        halfOpenSuccessCount.set(0);
        currentCount.set(0);
    }


}
