package com.github.zack.use.custom.circuit.breaker;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author zhouze
 * @date 2023/10/19
 */
public class CircuitBreaker {

    private State state;

    private Config config;

    private Counter counter;

    private long lastOpenedTime;


    public CircuitBreaker(Config config) {
        this.config = config;
        this.counter = new Counter(config.getFailureCount(), config.getFailureTimeInterval());
        this.state = State.CLOSED;
    }

    public <T> T run(Supplier<T> toRun, Function<Throwable, T> fallback) {
        try {
            if (state == State.OPEN) {
                // 判断 Half-Open 是否超时
                if (halfOpenTimeout()) {
                    return halfOpenHandle(toRun, fallback);
                }
                return fallback.apply(new RuntimeException("degrade by circuit breaker"));
            } else if (state == State.CLOSED) {
                T result = toRun.get();
                closed();
                return result;
            } else {
                return halfOpenHandle(toRun, fallback);
            }

        } catch (Exception e) {
            counter.incrFailureCount();
            if (counter.failureThresholdReached()) {
                //错误次数到达阈值，进入OPEN
                open();
            }

            return fallback.apply(e);
        }
    }

    private boolean halfOpenTimeout() {
        return (System.currentTimeMillis() - lastOpenedTime) > config.getHalfOpenTimeout();
    }

    private <T> T halfOpenHandle(Supplier<T> toRun, Function<Throwable, T> fallback) {
        try {
            // CLOSED 状态超时进入HALF-OPEN状态
            halfOpen();

            T result = toRun.get();
            int halfOpenCount = counter.incrSuccessHalfOpenCount();

            if (halfOpenCount >= this.config.getHalfOpenSuccessCount()) {
                // HALF-OPEN 状态成功次数到达阈值，进入CLOSED状态
                closed();
            }
            return result;
        } catch (Exception e) {
            open();
            return fallback.apply(new RuntimeException("degrade by circuit breaker"));
        }
    }

    private void halfOpen() {
        state = State.HALF_OPEN;
    }

    private void closed() {
        counter.reset();
        state = State.CLOSED;
    }

    private void open() {
        state = State.OPEN;
        lastOpenedTime = System.currentTimeMillis();
    }

}
